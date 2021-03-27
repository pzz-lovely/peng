package synchronizer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-03 19:54
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SynchronizedToLock {
    Object o = new Object();
    Lock lock = new ReentrantLock();

    public synchronized void method1(){
        System.out.println("我是synchronized形式的锁");
    }

    public void method2(){
        lock.lock();
        try{
            System.out.println("我是lock形式的锁");
        }finally {
            lock.unlock();
        }
    }

    public void method3(){
        synchronized (o){
            System.out.println("12314");
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock lock = new SynchronizedToLock();
        lock.method1();
        lock.method2();
        lock.method3();
    }

}
