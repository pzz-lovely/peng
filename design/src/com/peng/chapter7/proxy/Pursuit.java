package com.peng.chapter7.proxy;

/**
 * @Auther lovely
 * @Create 2020-04-28 10:07
 * @Description 追求者类
 */
public class Pursuit implements Gift {
    private SchoolGirl schoolGirl;
    private String name;

    public Pursuit(SchoolGirl schoolGirl, String name) {
        this.schoolGirl = schoolGirl;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String giftGiving() {
        return "巧克力";
    }
}
