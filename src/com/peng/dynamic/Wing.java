package com.peng.dynamic;

/**
 * @Author lovely
 * @Create 2020-12-24 14:23
 * @Description 翅膀
 */
public class Wing implements Bird {

    private String color;

    public Wing(String color) {
        this.color = color;
    }

    @Override
    public String fly() {
        return color + "翅膀 ,飞";
    }
}