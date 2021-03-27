package com.peng;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther lovely
 * @Create 2020-08-11 13:20
 * @Description CyclicBarrier的各种测试
 */
public class CyclicBarrierDemo {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        System.out.println("这一代执行完毕啦");
    });
    public static void main(String[] args) {
        test1();
        test1();
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
