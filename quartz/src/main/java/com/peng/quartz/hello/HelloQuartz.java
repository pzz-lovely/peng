package com.peng.quartz.hello;

import com.peng.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author lovely
 * @create 2021-03-31 9:39
 * @description
 */
public class HelloQuartz {
    public static void main(String[] args) throws SchedulerException {
        // 创建一个 jobDetail 的实例，将该实例与 MyJob.class 绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob").build();
        // 创建一个 Trigger 触发器的实例，定义该 job 立即执行，并且每 5 秒执行一次，一直执行
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger").startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                ).build();


        // 创建 Scheduler 任务调度器
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}