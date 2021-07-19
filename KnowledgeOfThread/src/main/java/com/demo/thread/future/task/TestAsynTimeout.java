package com.demo.thread.future.task;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author jieguangzhi
 * @date 2021-06-21
 */
@Slf4j
public class TestAsynTimeout {
    private static ThreadPoolExecutor instanceQueueConsumerThreadPool = new ThreadPoolExecutor(16, 16, 3L, TimeUnit.MINUTES,
            new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("instance-queue-consumer-%d").build(), new ThreadPoolExecutor.AbortPolicy());


    public static <T> CompletableFuture<T> within(CompletableFuture<T> future, long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        // 哪个先完成 就apply哪一个结果
        return future.applyToEither(timeoutFuture, Function.identity());
    }

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        Delayer.delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    public static void main(String[] args) {
        //1. 通过线程池来消费
        Supplier<String> command = () -> {
            Stopwatch stopwatch = Stopwatch.createStarted();
            //1. 从实例队列中取出实例上下文
            log.info("CompletableFuture使用的线程名称:{}", Thread.currentThread().getName());
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int sleep = random.nextInt(2) + 3;
            try {
                TimeUnit.MILLISECONDS.sleep(sleep * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}线程执行完毕", Thread.currentThread().getName());
            return "ok";
        };

        CompletableFuture<String> result = within(CompletableFuture.supplyAsync(command, instanceQueueConsumerThreadPool), 3, TimeUnit.SECONDS)
                .whenComplete((r, e) -> {
                    if (e == null) {
                        System.out.println("完成了");
                    }
                })
                .exceptionally(throwable -> {
                    log.error("error,线程的名称:{}",Thread.currentThread().getName());
                    throwable.printStackTrace();
                    main(null);
                    return "error";
                });

    }
}
