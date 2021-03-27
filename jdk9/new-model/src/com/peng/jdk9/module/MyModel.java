package com.peng.jdk9.module;

/**
 * @author lovely
 * @create 2021-03-11 10:32
 * @description
 */
public class MyModel {
    private String name;

    private String shape;

    public MyModel(String name, String shape) {
        this.name = name;
        this.shape = shape;
    }

    public MyModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}