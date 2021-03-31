package com.peng.quartz.simple;

import com.peng.quartz.job.SimpleJob1;
import com.peng.quartz.job.SimpleJob2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author lovely
 * @create 2021-03-31 14:20
 * @description JobDataMap调度类
 */
public class SimpleScheduler {
    public static void main(String[] args) throws SchedulerException {
        // 创建一个 jobDetail 的实例，并将该实例与 SimpleJob.class 绑定
        JobDetail jobDetail = JobBuilder
                .newJob(SimpleJob2.class)    // 定义 Job 类为 HelloJob 类，真正执行逻辑所在
                .withIdentity("myJob", "group1") // 定义 name 和 group
                .usingJobData("message", "hello myJob1")    // 加入属性到 jobDataMap
                .usingJobData("floatJobValue", 8.88f)       // 加入属性到 jobDataMap
                .build();


        // 创建一个 Trigger触发器的实例，定义该 job 立即执行，并且没 2秒 执行一次，一直执行
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        // 创建 schedule 实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();      // 启动
        // jobDetail 和 trigger 加入调度
        scheduler.scheduleJob(jobDetail, trigger);
    }
}