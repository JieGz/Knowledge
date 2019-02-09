package com.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 通过实现{@link Job}接口,可以使一个普通的Java类，化身为可调度的任务
 *
 * @author 揭光智
 * @date 2018/12/22
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getTrigger().getName() + "  trigger time is ==>" + new Date());
    }
}