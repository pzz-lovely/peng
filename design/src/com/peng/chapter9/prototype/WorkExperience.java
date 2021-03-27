package com.peng.chapter9.prototype;

import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-04-28 10:43
 * @Description todo
 */
public class WorkExperience implements Cloneable {
    private Date workDate;

    public WorkExperience(Date workDate) {
        this.workDate = workDate;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
