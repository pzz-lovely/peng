package com.peng.chapter3;

import mldn.arrays.ArraysDemo;

import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-06-30 19:43
 * @Description 联系抛硬币
 */
public class Demo7 {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int coin = random.nextInt();
            System.out.println((coin%2==0? '正':'反'));
        }


    }
}
