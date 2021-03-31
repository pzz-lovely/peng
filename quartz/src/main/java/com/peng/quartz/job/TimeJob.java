package com.peng.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author lovely
 * @create 2021-03-31 14:51
 * @description
 */
public class TimeJob extends HelloJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime dateTime = LocalDateTime.now();
        LOGGER.info("现在的时间是：{}", dateTimeFormatter.format(dateTime));

        // 具体执行的业务
        LOGGER.info("具体执行的业务...");
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        Trigger trigger = jobExecutionContext.getTrigger();
        LOGGER.info("开始的时间：{}",
                dateTimeFormatter.format(trigger.getStartTime().toInstant().atZone(ZoneId.systemDefault())));
        LOGGER.info("结束的时间：{}",
                dateTimeFormatter.format(trigger.getEndTime().toInstant().atZone(ZoneId.systemDefault())));
    }
}