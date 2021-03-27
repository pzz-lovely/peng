package com.peng;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-08-11 13:20
 * @Description CyclicBarrier的各种测试
 *
 *  测试1：当线程数量都到达了屏障数，会重新开始新的屏障设置
 *
 */
public class CyclicBarrierDemo {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        System.out.println("这一代都到期啦");
    });
    public static void main(String[] args) {
        //test1();
        // test1();
        Random random = new Random();
        int[] ints = IntStream.range(1, 20).map(random::nextInt).toArray();
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    // 测试当线程数都 到达屏障数的时候，会不会换下一代
    private static void test1() {
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "到达了，等待");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "开始行动");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
