package com.demo.thread.future.task;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author jieguangzhi
 * @date 2021-06-21
 */
public class Delayer {
    static ScheduledFuture<?> delay(Runnable command, long delay,
                                    TimeUnit unit) {
        return delayer.schedule(command, delay, unit);
    }

    static final class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("CompletableFutureDelayScheduler");
            return t;
        }
    }

    static final ScheduledThreadPoolExecutor delayer;

    // 注意，这里使用一个线程就可以搞定 因为这个线程并不真的执行请求 而是仅仅抛出一个异常
    static {
        (delayer = new ScheduledThreadPoolExecutor(
                1, new DaemonThreadFactory())).
                setRemoveOnCancelPolicy(true);
    }
}
