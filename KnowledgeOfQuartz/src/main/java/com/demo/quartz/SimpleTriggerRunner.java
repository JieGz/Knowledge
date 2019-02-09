package com.demo.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 使用SimpleTrigger来调度SimpleJob
 *
 * @author 揭光智
 * @date 2018/12/22
 */
public class SimpleTriggerRunner {

    public static void main(String[] args) {
        try {
            //1:创建一个JobDetail实例，指定SimpleJob
            JobDetail jobDetail = new JobDetail("job1_1", "group1_1", SimpleJob.class);

            //2:通过SimpleTrigger触发器，定义调度规则，马上行动，每10秒运行一次，一共运行5次
            SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1", "tGroup1_1");
            simpleTrigger.setStartTime(new Date());
            simpleTrigger.setRepeatInterval(10 * 1000);
            simpleTrigger.setRepeatCount(5);

            //3:通过SchedulerFactory获取一个调度器实例
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            //4:将任务实例jobDetail和触发器注册到调度器里面
            scheduler.scheduleJob(jobDetail, simpleTrigger);

            //5:开始调度
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
