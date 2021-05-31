package com.test.quartz;

import com.demo.quartz.SimpleJob;
import org.junit.Test;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.locks.LockSupport;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * @author jieguangzhi
 * @date 2021-05-18
 */
public class QuartzExecutors {

    @Test
    public void testQuartz() {
        try {
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();


            for (int i = 0; i < 1000; i++) {
                JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("Luke JobDetail" + i).build();
                scheduler.addJob(jobDetail, false, true);

                //TriggerKey triggerKey = new TriggerKey("Luke JobDetail" + i, "Luke JobDetail Group" + i);

                CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey("myTrigger" + i, "myTriggerGroup" + i)).startAt(new Date()).endAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).plusMinutes(5L).toInstant()))
                        .withSchedule(cronSchedule("0 * * * * ?").withMisfireHandlingInstructionDoNothing())
                        .forJob(jobDetail).build();

                scheduler.scheduleJob(cronTrigger);
            }
            //TimeUnit.MINUTES.sleep(5L);
            LockSupport.park(this);
        } catch (SchedulerException /*| InterruptedException*/ e) {
            e.printStackTrace();
        }

    }
}
