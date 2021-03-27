package locks.lock.readwrite;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther lovely
 * @Create 2020-03-21 17:07
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 自旋锁
 */
public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!sign.compareAndSet(null,thread)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        new Thread(() -> spinLock.getLockOption()).start();
        new Thread(() -> spinLock.getLockOption()).start();
    }

    public void getLockOption(){
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始尝试获自旋锁");
        lock();
        System.out.println(name + "获取到了自旋锁");
        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            unlock();
            System.out.println(name+"释放了锁");
        }
    }
}
