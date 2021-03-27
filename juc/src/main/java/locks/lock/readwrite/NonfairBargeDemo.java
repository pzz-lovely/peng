package locks.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther lovely
 * @Create 2020-03-21 14:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class NonfairBargeDemo {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private static void read(){
        String currentName = Thread.currentThread().getName();
        System.out.println(currentName+"开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(currentName + "得到读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(currentName+"释放读锁");
            readLock.unlock();
        }
    }


    private static void write(){
        String currentName = Thread.currentThread().getName();
        System.out.println(currentName+"开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(currentName + "得到读锁，正在写入");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(currentName+"释放写锁");
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {
        //第一步
        new Thread(() -> write(), "Thread1").start();
        //第二拿到读锁
        new Thread(() -> read(), "Thread2").start();
        //在队列中 头节点等待的读锁 ，后续的新加入的读线程可以直接插队到 前面。  这个线程3得到了读锁了，下一个节点为写线程  后续插队的线程就不能插队了。
        new Thread(() -> read(), "Thread3").start();
        new Thread(() -> write(), "Thread4").start();
        new Thread(() -> read(), "Thread5").start();
        new Thread(() -> {
            Thread[] threads = new Thread[1000];
            //创建子线程
            for (int i = 0; i < 1000; i++) {
                threads[i] = new Thread(() -> read(), "子线程Thread" + i);
            }
            for (int i = 0; i < 1000; i++) {
                threads[i].start();
            }
        }, "Thread6").start();
    }
}
