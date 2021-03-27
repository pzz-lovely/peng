package com.peng.jdk9.streamapi;

import java.util.List;

/**
 * @author lovely
 * @create 2021-03-11 12:07
 * @description
 */
public class NewStreamApiDemo {
    public static void main(String[] args) {
        // 从第一个开始，满足条件则取出，循环下一个，只要遇到不满足条件的，直接停止
        List<Integer> list = List.of(25, 23, 214, 11, 56, 99, 44, 66);

        list.stream().takeWhile(i -> i > 12).forEach(System.out::println);
    }
}