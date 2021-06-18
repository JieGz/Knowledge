package com.demo.thread.future.task;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

/**
 * @author jieguangzhi
 * @date 2021-06-01
 */
@Slf4j
public class FutureTaskDemo {
    private static ThreadPoolExecutor instanceQueueConsumerThreadPool = new ThreadPoolExecutor(16, 16, 3L, TimeUnit.MINUTES,
            new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("instance-queue-consumer-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < instanceQueueConsumerThreadPool.getCorePoolSize() / 2; i++) {
            test3();
        }
        LockSupport.park(FutureTaskDemo.class);
    }

    public static void test1() {
        FutureTask<Stopwatch> task = new FutureTask<>(() -> {
            Stopwatch started = Stopwatch.createStarted();
            System.out.println("do something");
            TimeUnit.SECONDS.sleep(2L);
            return started;
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(task);
        try {
            System.out.println(task.get().elapsed(TimeUnit.SECONDS) + "s");
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }


    public static void test2() {
        long l = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("在回调中执行耗时操作...");
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        completableFuture = completableFuture.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("在回调的回调中执行耗时操作...");
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i + 100;
            });
        });

        completableFuture.whenComplete((result, e) -> {
            System.out.println("计算结果:" + result);
        });

        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + " ms");

    }

    public static void test3() {
        //1. 通过线程池来消费
        Supplier<SchedulerBean> command = () -> {
            Stopwatch stopwatch = Stopwatch.createStarted();
            //1. 从实例队列中取出实例上下文
            log.info("CompletableFuture使用的线程名称:{}", Thread.currentThread().getName());
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int sleep = random.nextInt(4) + 3;
            try {
                TimeUnit.MILLISECONDS.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //2.
            if (sleep < 4) {
                try {
                    int a = sleep / 0;
                } catch (Exception e) {
                    return SchedulerBean.builder().stopwatch(stopwatch).flag(false).exceptionMessage(e.getMessage()).build();
                }
            }
            return SchedulerBean.builder().stopwatch(stopwatch).flag(true).build();
        };
        CompletableFuture.supplyAsync(command, instanceQueueConsumerThreadPool).whenComplete((schedulerBean, e) -> {
            Stopwatch stopwatch = schedulerBean.getStopwatch();
            if (e == null && schedulerBean.getFlag()) {
                log.info("succeed: 一共使用了:{}s", stopwatch.elapsed(TimeUnit.SECONDS));
            } else {
                log.error("defeated: 一共使用了:{}ms,失败原因:{}", stopwatch.elapsed(TimeUnit.MILLISECONDS),schedulerBean.getExceptionMessage());
            }
            test3();
        });
    }

    public static void test4() {
        test4();
    }
}
