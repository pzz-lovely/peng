package threadpool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-19 17:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示每个任务执行前后放 钩子函数
 */
public class PauseableThreadPool extends ThreadPoolExecutor {
    private boolean isPaused;
    Lock lock = new ReentrantLock();
    private Condition unPaused = lock.newCondition();
    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    private void pause(){
        lock.lock();
        try{
            isPaused = true;
        }finally {
            lock.unlock();
        }
    }

    private void resume(){
        lock.lock();
        try{
            isPaused  = false;
            unPaused.signalAll();
        }finally {
            lock.unlock();
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try{
            while (isPaused) {
                unPaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        PauseableThreadPool threadPool = new PauseableThreadPool(10, 20, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(()->{
                System.out.println("我被执行了");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1500);
        threadPool.pause();
        System.out.println("线程池被暂停了");
        Thread.sleep(1500);
        threadPool.resume();
        System.out.println("线程池被恢复了");
    }

}
