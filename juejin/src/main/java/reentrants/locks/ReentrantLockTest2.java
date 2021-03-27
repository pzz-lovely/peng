package reentrants.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在java条件锁的实现都在AQS的ConditionObject类中，Condition接口
 * 一个线程等待条件，另一个线程通知条件已成立，后面的数字代表代码实际运行的顺序，
 */
public class ReentrantLockTest2 {
    public static void main(String[] args) throws InterruptedException {
        //声明一个重入锁
        ReentrantLock lock = new ReentrantLock();
        //声明一个条件锁
        Condition condition = lock.newCondition();
        new Thread(()->{
            try{
                lock.lock();    //1
                System.out.println("before await");//2
                //等待条件 睡眠
                condition.await();//3
                System.out.println("after await");//10
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();//11
            }
        }).start();
        //这里谁1s是为了让上面的线程先获取到锁
        Thread.sleep(1000);
        lock.lock();//4
        try{
            //这里睡2s代码表表这个线程执行业务需要时间
            Thread.sleep(2000);//5
            System.out.println("before signal");//6
            //通知条件已成立
            condition.signal();//7
            System.out.println("after signal");//8
        }finally {
            lock.unlock();//9
        }

    }

}
