package com.peng.chapter13.build;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:52
 * @Description 指挥官
 */
public class Director {
    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
