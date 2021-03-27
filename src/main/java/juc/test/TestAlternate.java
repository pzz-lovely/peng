package juc.test;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AlternativeDemo implements Runnable {
    private char nextThread = 'A';
    private Lock lock = new ReentrantLock();    //可重入锁
    private Condition[] conditions;
    private int totalTimes;

    /**
     *
     * @param threadNum 线程数量
     * @param totalTimes
     */
    public AlternativeDemo(int threadNum, int totalTimes) {
        this.totalTimes = totalTimes;
        conditions = new Condition[threadNum];
        for (int i = 0; i < threadNum; i++) {
            conditions[i] = lock.newCondition();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i <= totalTimes; i++) {
            lock.lock();
            char currentThreadName = Thread.currentThread().getName().charAt(0);
            try{
                if (nextThread != currentThreadName) {
                    conditions[currentThreadName-'A'].await();
                }
                System.out.println(currentThreadName + "\t" + i);
                nextThread = (char) ('A' + (nextThread + 1 - 'A') % conditions.length);
                conditions[nextThread-'A'].signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
}
public class TestAlternate {
    public static void main(String[] args) {
        int threadNum = 3 ;
        AlternativeDemo atomicDemo = new AlternativeDemo(threadNum, 10);
    }
}
