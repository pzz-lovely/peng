package com.peng.quartz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.time.LocalDateTime;

/**
 * @author lovely
 * @create 2021-03-31 14:07
 * @description 基于 getter/setter 方式
 */
public class SimpleJob2 extends CronJob {

    private String message;

    private Float floatJobValue;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("jobDataMap定义的 message 的值：{}", message);
        LOGGER.info("jobDataMap定义的 message 的值：{}", floatJobValue);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getFloatJobValue() {
        return floatJobValue;
    }

    public void setFloatJobValue(Float floatJobValue) {
        this.floatJobValue = floatJobValue;
    }
}