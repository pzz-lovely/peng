package control;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther lovely
 * @Create 2020-03-25 20:49
 * @PACKAGE_NAME I  ntelliJ IDEA
 * @Description
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("所有人已到齐，一起行动");
        });
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(new Task(i, barrier)).start();
        }
        barrier.reset();
        Thread.sleep(3000);
        System.out.println("第二次集结");
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(new Task(i, barrier)).start();
        }
    }

    static class Task implements Runnable {
        private int id;
        private CyclicBarrier barrier;

        public Task(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try{
                System.out.println(id+" 到达了集结点，开始等待");
                Thread.sleep(2000);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(id+"，开始行动");
        }
    }
}
