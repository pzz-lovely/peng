package atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther lovely
 * @Create 2020-03-22 17:47
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 自旋锁
 */
public class SpinLock {
    private static AtomicReference<Thread> atomic = new AtomicReference<>();

    public static void lock(){
        Thread current = Thread.currentThread();
        while(!atomic.compareAndSet(null, current));
    }

    public static void unlock(){
        Thread current = Thread.currentThread();
        atomic.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            try{
                lock();
                System.out.println("线程1获取到锁了");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
                System.out.println("线程1释放了锁");
            }
        });
        Thread thread2 = new Thread(()->{
            lock();
            System.out.println("线程2获取到了锁");
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
                System.out.println("线程2释放了锁");
            }
        });
        thread1.start();
        thread2.start();
    }
}
