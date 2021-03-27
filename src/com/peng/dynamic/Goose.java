package com.peng.dynamic;

/**
 * @Author lovely
 * @Create 2020-12-24 14:13
 * @Description todo
 */
public class Goose implements Animal, Bird {



    @Override
    public String run() {
        return "跑";
    }

    @Override
    public String fly() {
        return "飞";
    }
}