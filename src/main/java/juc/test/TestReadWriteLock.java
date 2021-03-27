package juc.test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteLockDemo{
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private int data = 2;
    public void get(){
        lock.readLock().lock();
        try{
            System.out.println("读操作  " + Thread.currentThread().getName() + " : " + data);
        }finally{
            lock.readLock().unlock();
        }
    }

    public void set(int data) {
        lock.writeLock().lock();
        try{
            System.out.println("写操作  " + Thread.currentThread().getName() + " : " + data);
            this.data = data;
        }finally{
            lock.writeLock().unlock();
        }
    }
}

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                demo.get();
            }, "get"+i).start();
            new Thread(()->{
                demo.set(222);
            },"set").start();
        }
    }
}
