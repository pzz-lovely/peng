package com.peng.jdk9.modeltest;

/**
 * @author lovely
 * @create 2021-03-11 10:45
 * @description
 */
public class TestModel {
    private int size;

    private double price;

    public TestModel(int size, double price) {
        this.size = size;
        this.price = price;
    }

    public TestModel() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}