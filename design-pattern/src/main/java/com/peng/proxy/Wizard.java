package com.peng.proxy;

/**
 * @author lovely
 * @create 2021-03-08 17:37
 * @description
 */
public class Wizard {
    private final String name;

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "name='" + name + '\'' +
                '}';
    }
}