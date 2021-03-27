package threadcoreknowledge.threadobjectclasscommonmethods;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Auther lovely
 * @Create 2020-03-09 8:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 展示wait和notify的基本用法
 * 1.研究代码执行顺序
 * 2.证明wait释放锁
 */
public class Wait {
    public static Object object = new Object();


    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程1开始执行了");
                try{
                    //释放了锁
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }


    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"使用了notify()");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }
}
