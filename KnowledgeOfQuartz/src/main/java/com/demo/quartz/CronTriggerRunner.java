package com.demo.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author 揭光智
 * @date 2020/03/04
 */
public class CronTriggerRunner {
//    public static void main(String[] args) {
//
//        try {
//            //1:创建一个JobDetail实例，指定SimpleJob
//            JobDetail jobDetail = new JobDetail("job1_1", "group1_1", SimpleJob.class);
//
//            CronTrigger cronTrigger = new CronTrigger("trigger1_1", "tGroup1_1", "0 * 14,15 * * ?");
//
//            //3:通过SchedulerFactory获取一个调度器实例
//            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
//            Scheduler scheduler = schedulerFactory.getScheduler();
//
//            //4:将任务实例jobDetail和触发器注册到调度器里面
//            scheduler.scheduleJob(jobDetail, cronTrigger);
//
//            //5:开始调度
//            scheduler.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
