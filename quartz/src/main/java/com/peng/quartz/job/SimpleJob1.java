package com.peng.quartz.job;

import org.quartz.*;

import java.time.LocalDateTime;

/**
 * @author lovely
 * @create 2021-03-31 14:07
 * @description 基于 JobDataMap形式的获取值
 */
public class SimpleJob1 extends CronJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime dateTime = LocalDateTime.now();
        LOGGER.info("现在的时间是：{}", dateTimeFormatter.format(dateTime));
        // 具体的业务逻辑
        LOGGER.info("开始生成任务报表 或 开始发送邮件...");
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        // 打印 jobDetail 的 name
        LOGGER.info("jobDetail 的 name：{}", key.getName());
        // 打印 jobDetail 的 group
        LOGGER.info("jobDetail 的 group：{}", key.getGroup());

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String message = jobDataMap.getString("message");
        float floatJobValue = jobDataMap.getFloat("floatJobValue");

        // 打印 jobDataMap 定义的 message 的值
        LOGGER.info("jobDataMap定义的 message 的值：{}", message);
        // jobDataMap定义的 floatJobValue 的值
        LOGGER.info("jobDataMap定义的 floatJobValue 的值：{}", floatJobValue);

    }
}