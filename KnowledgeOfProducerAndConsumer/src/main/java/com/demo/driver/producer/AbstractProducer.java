package com.demo.driver.producer;


import com.demo.driver.IProducer;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public abstract class AbstractProducer implements IProducer, Runnable {

    @Override
    public void producer() {
        doProducer();
    }

    @Override
    public void run() {
        producer();
    }

    protected abstract void doProducer();
}
