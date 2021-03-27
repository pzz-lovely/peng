package locks.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sun.misc.Version.print;

/**
 * @Auther lovely
 * @Create 2020-03-21 13:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示贡公平 和不公平的情况
 */
public class FairLock {
    public static void main(String[] args) throws InterruptedException {
        PrintQueue queue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始打印 ");
                queue.printJob(queue);
                System.out.println("打印完毕");
            });
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            Thread.sleep(100);
        }
    }

    static class PrintQueue {
        private Lock queueLock = new ReentrantLock(false);

        public void printJob(Object document) {
            queueLock.lock();
            try {
                print();
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }

        private void print() throws InterruptedException {

            /*long duration = new Random().nextInt(10) + 1;*/
            System.out.println(Thread.currentThread().getName() + " 正在打印，需要 " + 1);
            Thread.sleep(1 * 1000);

        }
    }
}
