package com.peng.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lovely
 * @create 2021-03-31 9:40
 * @description
 */
public class HelloJob implements Job {

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 打印当前执行时间
        LocalDateTime date = LocalDateTime.now();

        LOGGER.info("现在的时间是：{}", dateTimeFormatter.format(date));
        // 具体的业务逻辑
        LOGGER.info("Hello Quartz");
    }
}