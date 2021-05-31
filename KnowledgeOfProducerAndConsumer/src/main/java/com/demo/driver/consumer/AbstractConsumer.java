package com.demo.driver.consumer;

import com.demo.driver.IConsumer;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public abstract class AbstractConsumer implements IConsumer, Runnable {
    @Override
    public void consumer() {
        doConsumer();
    }

    @Override
    public void run() {
        consumer();
    }

    protected abstract void doConsumer();
}
