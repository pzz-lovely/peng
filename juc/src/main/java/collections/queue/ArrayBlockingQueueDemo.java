package collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Auther lovely
 * @Create 2020-03-25 15:35
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ArrayBlockingQueueDemo {
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        Interviewer interviewer = new Interviewer(queue);
        Consumer consumer = new Consumer(queue);
        Thread thread1 = new Thread(interviewer);
        Thread thread2 = new Thread(interviewer);
        thread1.start();
        thread2.start();

    }
}

class Interviewer implements Runnable {
    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue<String>  queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("十个获选人都来了：");
        for (int i = 0; i < 10; i++) {
            String candidate = "Candidate" + i;
            try {
                queue.put(candidate);
                System.out.println("安排好了" + candidate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            queue.put("Stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg = null;
        try {
            msg = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (msg.equals("Stop")) {
            System.out.println(msg+"到了");
        }
        System.out.println("所有候选人都结束了");
    }
}