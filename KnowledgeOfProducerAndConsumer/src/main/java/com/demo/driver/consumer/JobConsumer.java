package com.demo.driver.consumer;

import com.demo.driver.IMessageQueue;
import com.demo.driver.Job;
import com.demo.driver.engine.BackupServer;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class JobConsumer extends AbstractConsumer {

    private final IMessageQueue<Job> messageQueue;

    public JobConsumer(IMessageQueue<Job> messageQueue) {
        this.messageQueue = messageQueue;
    }


    @Override
    protected void doConsumer() {
        //1.先判断底层引擎是否繁忙
        if (BackupServer.isBusy()) {
            System.out.println("******************底层服务处理繁忙状态***************");
            return;
        }
        Job message = messageQueue.getMessage();

    }
}
