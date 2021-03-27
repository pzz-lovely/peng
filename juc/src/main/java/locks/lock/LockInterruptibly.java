package locks.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-21 9:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class LockInterruptibly implements Runnable {

    private Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        LockInterruptibly l = new LockInterruptibly();
        Thread thread1 = new Thread(l);
        Thread thread2 = new Thread(l);
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        thread2.interrupt();
    }

    @Override
    public void run() {
        String currentName = Thread.currentThread().getName();
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try{
            lock.lockInterruptibly();
            try{
                System.out.println(currentName+"获取到了锁");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.out.println("睡眠期间被中断了");
            } finally {
                lock.unlock();
                System.out.println(currentName+"释放掉了锁");


            }
        } catch (InterruptedException e) {
            System.out.println(currentName+"获取锁的过程中中断了");
        }
    }
}
