package com.peng;

import java.sql.Time;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-08-11 18:57
 * @Description todo
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
            reentrantLock.lock();
            reentrantLock.lock();

            try {
                System.out.println("Thread1 等待");
                Thread.sleep(1000);
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reentrantLock.unlock();
            reentrantLock.unlock();

            //reentrantLock.unlock();
            //reentrantLock.unlock();
        }).start();

        new Thread(()->{
            reentrantLock.lock();
            condition.signal();
            System.out.println("唤醒 Thread1");
            reentrantLock.unlock();
        }).start();

    }
}
