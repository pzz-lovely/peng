package com.peng.arr.entity;

/**
 * @author lovely
 * @create 2021-03-06 9:36
 * @description
 */
public interface IArray<T extends Number> {
    T get(int index);


    void set(int index, T value);

    int length();


}