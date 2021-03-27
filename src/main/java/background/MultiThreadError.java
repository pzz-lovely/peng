package background;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-12 14:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示死锁
 */
public class MultiThreadError {
    static  int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> deadlock()).start();
        new Thread(() -> deadlock()).start();
    }


    public static void deadlock(){
        if(flag == 1){
            synchronized (o1){
                System.out.println("进入1");
                synchronized (o2){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入2");
                }
            }
        }else{
            synchronized (o2){
                System.out.println("进入2");
                synchronized (o1){
                    System.out.println("进入1");
                }
            }
        }
    }
}
