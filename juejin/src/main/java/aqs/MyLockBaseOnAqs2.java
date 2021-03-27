package aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class MyLockBaseOnAqs2 {
    private static class Sync extends AbstractQueuedSynchronizer {
        //实现tryAcquire(acquires)

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //实现tryRelease(releases)方法
        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }


    private Sync sync = new Sync();

    public void lock(){
        sync.tryAcquire(1);
    }

    public void unlock(){
        sync.tryRelease(1);
    }

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        MyLockBaseOnAqs2 lock = new MyLockBaseOnAqs2();
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
            lock.lock();

            try {
                IntStream.range(0, 10000).forEach(j -> {
                    count++;
                });
            } finally {
                lock.unlock();
            }
//            System.out.println(Thread.currentThread().getName());
            countDownLatch.countDown();
        }, "tt-" + i).start());
        countDownLatch.await();
        System.out.println(count);
    }
}
