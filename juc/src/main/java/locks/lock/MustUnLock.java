package locks.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-21 9:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description Lock不会像synchronized一样 异常的时候释放锁
 * 最佳 情况 在 finally释放锁以便 保证发生异常的时候 一定被释放
 */
public class MustUnLock {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        try{
            lock.lock();
            //获取本所保护的资源
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        }finally {
            lock.unlock();
        }
        System.out.println("执行完毕");
    }
}
