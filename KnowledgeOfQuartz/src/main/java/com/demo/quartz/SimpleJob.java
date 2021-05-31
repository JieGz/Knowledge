package com.demo.quartz;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 通过实现{@link Job}接口,可以使一个普通的Java类，化身为可调度的任务
 *
 * @author 揭光智
 * @date 2018/12/22
 */
@Slf4j
public class SimpleJob implements Job {

    private Queue<TestBean> queue = new PriorityBlockingQueue<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //  System.out.println(context.getTrigger().getJobKey() + "  trigger time is ==>" + new Date());

        Stopwatch started = Stopwatch.createStarted();
        try {
            TimeUnit.MILLISECONDS.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int instCount = 10;
        for (int i = 0; i < instCount; i++) {
            TestBean bean = new TestBean();
            bean.setPriority("a");
            bean.setPriority("b");
            bean.setPriority("c");
            bean.setPriority("d");
            queue.offer(bean);
        }
        long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
        log.info("triggerName:{},创建{}个对象,需要花费{}秒时间", context.getTrigger().getKey().getName(), instCount, elapsed);
    }
}