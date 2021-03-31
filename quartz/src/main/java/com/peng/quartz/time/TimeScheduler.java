package com.peng.quartz.time;

import com.peng.quartz.job.TimeJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lovely
 * @create 2021-03-31 14:56
 * @description
 */
public class TimeScheduler {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TimeJob.class).withIdentity("timeJob").build();
        Calendar calendar = Calendar.getInstance();
        // 开始时间
        calendar.add(Calendar.SECOND, 3);
        Date startTime = calendar.getTime();
        // 结束时间
        calendar.add(Calendar.SECOND, 6);
        Date endTime = calendar.getTime();
        SimpleTrigger timeTrigger = TriggerBuilder.newTrigger()
                .withIdentity("timeTrigger")
                .startAt(startTime)
                .endAt(endTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        // 创建 Scheduler实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, timeTrigger);
    }
}