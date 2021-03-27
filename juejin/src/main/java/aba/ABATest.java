package aba;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ABATest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        new Thread(()->{
            int value = atomicInteger.get();
            System.out.println("Thread 1 read value " + value);
            //阻塞一秒
            LockSupport.parkNanos(1000000000L);
        }).start();
    }
}
