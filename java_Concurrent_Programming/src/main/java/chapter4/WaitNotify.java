package chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Wait wait = new Wait();
        Notify notify = new Notify();
        new Thread(wait).start();
        new Thread(notify).start();;
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                //当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try{
                        System.out.println(Thread.currentThread() + " flag is true.wait @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //条件满足时完成工作
                System.out.println(Thread.currentThread() + " flag is false .running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }



    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁
                //直到当前线程释放了lock后，waitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock . notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again . sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
