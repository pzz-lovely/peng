package control;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-25 20:26
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description Condition基本用法
 */
public class ConditionDemo1 {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    static void method1(){
        lock.lock();
        try{
            System.out.println("条件不满足，开始await");
            //等待会放弃掉锁
            condition.await();
            System.out.println("条件满足了开始执行后续的任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    static void method2(){
        lock.lock();
        try{
            System.out.println("准备工作完成，开始signal");
            Thread.sleep(1000);
            condition.signal();
            System.out.println("工作完成了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        new Thread(() -> method1()).start();
        new Thread(()->method2()).start();
    }
}
