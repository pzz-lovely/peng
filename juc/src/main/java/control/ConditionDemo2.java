package control;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-25 20:33
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ConditionDemo2 {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try{
                    while (queue.size() == 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    notFull.signalAll();
                    System.out.println("从队列中取走一个数据。队列还剩" + queue.size());
                }finally {
                    lock.unlock();
                }
            }
        }
    }



    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try{
                    while (queue.size() == queueSize) {
                        System.out.println("队列满，等待有空余");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("从队列中插入一个数据。[生产者]队列还剩" + (queueSize-queue.size()));
                }finally {
                    lock.unlock();
                }
            }
        }
    }

}
