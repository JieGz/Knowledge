package com.demo.driver.queue;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class JobPriorityMessageQueue<MSG> extends AbstractMessageQueue<MSG> {
    private ObjectMapper objectMapper = new ObjectMapper();

    private final BlockingQueue<MSG> messageQueue;

    public JobPriorityMessageQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public JobPriorityMessageQueue(int initialCapacity) {
        this.messageQueue = new PriorityBlockingQueue<>(initialCapacity);
    }

    @Override
    protected void doReceiveMessage(List<MSG> messages) {
        for (MSG message : messages) {
            messageQueue.offer(message);
        }
        try {
            System.out.println("===>生产了一条新消息,队列中最新的数据size:" + messageQueue.size() + " 队列中最新的数据size:" + messageQueue.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected MSG doGetMessage() {
        try {
            System.out.println("<===消费了一条数据===,还剩:" + messageQueue.size());
            return messageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
