package com.peng.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @author lovely
 * @create 2021-03-31 10:25
 * @description
 */
public class CronJob extends HelloJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 打印当前执行时间
        LocalDateTime dateTime = LocalDateTime.now();
        LOGGER.info("现在的时间是：{}", dateTimeFormatter.format(dateTime));
        // 具体的业务逻辑
        LOGGER.info("开始生成任务报表，或开始发送邮件");
    }
}