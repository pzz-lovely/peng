package com.peng.quartz.cron;

import com.peng.quartz.job.CronJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author lovely
 * @create 2021-03-31 10:28
 * @description
 */
public class CronScheduler {
    public static void main(String[] args) throws SchedulerException {
        // jobDetail 保存着 job任务的信息
        JobDetail jobDetail = JobBuilder.newJob(CronJob.class).withIdentity("cronTrigger").build();

        // cronTrigger
        // 每日10点55分触发
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 55 10 1/1 * ? ")).build();


        // Schedule实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}