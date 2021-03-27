package threadcoreknowledge.threadobjectclasscommonmethods;

import netscape.security.UserTarget;

/**
 * @Auther lovely
 * @Create 2020-03-09 8:53
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 3个线程，线程1和线程2首先会被阻塞，线程3唤醒它们。notify,notifyAll
 * start先执行 代表线程先启动
 */
public class WaitNotifyAll {
    public static Object object = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName()+"线程开始执行了");
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
                object.notifyAll();
                System.out.println("线程"+Thread.currentThread().getName()+"使用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread1();
        Thread thread3 = new Thread2();
        thread1.start();
        thread2.start();
        Thread.sleep(500);
        thread3.start();
    }



}
