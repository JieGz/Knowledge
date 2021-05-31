package com.demo.driver.producer;


import com.demo.driver.IMessageQueue;
import com.demo.driver.Job;
import com.demo.driver.job.SchedulerJob;
import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class JobProducer extends AbstractProducer {

    private final IMessageQueue<Job> messageQueue;

    public JobProducer(IMessageQueue<Job> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    protected void doProducer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        //System.out.println(LocalDateTime.now().format(formatter) + "  " + Thread.currentThread().getName());
        List<Job> jobs = new ArrayList<>(100);
        for (int i = 0; i < 600; i++) {
            jobs.add(SchedulerJob.builder().name(("Luke" + new Random().nextInt(30))).build());
        }
        //向优先队列中推送一条消息
        HashedWheelTimer wheelTimer = new HashedWheelTimer(new DefaultThreadFactory("test"), 1, TimeUnit.MILLISECONDS, 1024, false);

        wheelTimer.newTimeout((timeout) -> {
            this.messageQueue.receiveMessage(jobs);
        }, 10, TimeUnit.SECONDS);


    }
}
