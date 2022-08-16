package com.demo;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadFactory;

/**
 * @author jieguangzhi
 * @date 2022-08-16
 */
public class OrderEventThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@Nonnull Runnable r) {
        final Thread thread = new Thread(r);
        thread.setName("order-event");
        return thread;
    }
}
