package com.demo.thread.lock;

import com.demo.thread.future.task.SchedulerBean;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author jieguangzhi
 * @date 2021-06-04
 */
@Slf4j
public class LockApp {
    private static ThreadPoolExecutor instanceQueueConsumerThreadPool = new ThreadPoolExecutor(128, 128, 3, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("instance-queue-consumer-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    private static Map<String, User> instance = new ConcurrentHashMap<>(4000);

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            User user = new User();
            user.setLock(String.valueOf(i % 1000 == 0 ? i - 1 : i));
            Supplier<SchedulerBean> command = () -> {
                while (true) {
                    synchronized (LockApp.class) {
                        if (!instance.containsKey(user.getLock())) {
                            instance.put(user.getLock(), user);
                            user.setHasLock(true);
                        }
                    }
                    if (user.isHasLock()) {
                        //do something
                        log.info(user.getUsername() + "_" + user.getLock());
                        sleep(200L);
                        break;
                    }
                    System.out.println("77777");
                    sleep(10L);
                }
                return SchedulerBean.builder().stopwatch(stopwatch).user(user).flag(true).build();
            };
            CompletableFuture.supplyAsync(command, instanceQueueConsumerThreadPool).whenComplete((schedulerBean, e) -> {
                Stopwatch stopwatch1 = schedulerBean.getStopwatch();
                if (e == null && schedulerBean.getFlag()) {
                    log.info("用户:{},succeed: 一共使用了:{}ms", schedulerBean.getUser().getUsername() + "_" + schedulerBean.getUser().getLock(), stopwatch1.elapsed(TimeUnit.MILLISECONDS));
                    instance.remove(schedulerBean.getUser().getLock());
                } else {
                    log.error("defeated: 一共使用了:{}ms,失败原因:{}", stopwatch1.elapsed(TimeUnit.MILLISECONDS), schedulerBean.getExceptionMessage());
                }
            });
        }
        log.info("主线程结束,一共使用了{}ms", System.currentTimeMillis() - begin);
    }


    public static void sleep(Long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
