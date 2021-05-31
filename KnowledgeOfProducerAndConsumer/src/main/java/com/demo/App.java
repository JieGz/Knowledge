package com.demo;

import com.demo.driver.IMessageQueue;
import com.demo.driver.Job;
import com.demo.driver.consumer.JobConsumer;
import com.demo.driver.producer.JobProducer;
import com.demo.driver.queue.JobPriorityMessageQueue;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class App {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("producer-pool-%d").build();
        ScheduledThreadPoolExecutor producerExecutor = new ScheduledThreadPoolExecutor(5, threadFactory);

        IMessageQueue<Job> messageQueue = new JobPriorityMessageQueue<>();

        for (int i = 0; i < 5; i++) {
            Runnable producer = new JobProducer(messageQueue);
            producerExecutor.scheduleAtFixedRate(producer, 1, 60, TimeUnit.SECONDS);
        }

        ThreadFactory consumerThreadFactory = new ThreadFactoryBuilder().setNameFormat("consumer-pool-%d").build();
        ScheduledThreadPoolExecutor consumerExecutor = new ScheduledThreadPoolExecutor(20, consumerThreadFactory);

        for (int i = 0; i < 20; i++) {
            Runnable consumer = new JobConsumer(messageQueue);
            consumerExecutor.scheduleAtFixedRate(consumer, 1, 300, TimeUnit.MILLISECONDS);
        }

    }
}
