package com.demo.driver.queue;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 重试队列
 *
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class RetryPriorityMessageQueue<MSG> extends AbstractMessageQueue<MSG> {

    private final BlockingQueue<MSG> messageQueue;

    public RetryPriorityMessageQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public RetryPriorityMessageQueue(int initialCapacity) {
        this.messageQueue = new PriorityBlockingQueue<>(initialCapacity);
    }

    @Override
    protected void doReceiveMessage(List<MSG> messages) {
        for (MSG message : messages) {
            messageQueue.offer(message);
        }
    }

    @Override
    protected MSG doGetMessage() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
