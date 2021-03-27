package aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.stream.IntStream;

public class MyLockBaseOnAqs {
    //定义一个同步器，实现AQS类
    private static class Sync extends AbstractQueuedSynchronizer{
        //实现tryAcquire(acquires)方法
        @Override
        public boolean tryAcquire(int acquire){
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //实现tryRelease(release)方法
        @Override
        public boolean tryRelease(int release) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    //声明同步器
    private final Sync sync = new Sync();

    //加锁
    public void lock(){
        sync.acquire(1);
    }

    //释放锁
    public void unlock() {
        sync.release(1);
    }
    private static int count = 0 ;

    public static void main(String[] args) throws InterruptedException {
        MyLockBaseOnAqs lock = new MyLockBaseOnAqs();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        IntStream.range(0, 100).forEach(i -> new Thread(() -> {
            lock.lock();
            try {
                IntStream.range(0, 100).forEach(j -> {
                    count++;
                });
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();
        }, "tt-" + i).start());

        countDownLatch.await();
        System.out.println(count);
    }
}
