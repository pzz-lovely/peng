package com.peng.arr;

import com.peng.arr.entity.MyArray;

/**
 * @author lovely
 * @create 2021-03-06 9:35
 * @description 数组，可以实现随机查找
 * 支持 int,long
 */
public class ArrayApp {
    public static void main(String[] args) {
        MyArray<Integer> myArray = new MyArray<>(10);
        myArray.set(0, 100);
        myArray.set(3, 1);
        myArray.set(2, 3);
        myArray.set(1, 2);
        System.out.println(myArray);
    }
}