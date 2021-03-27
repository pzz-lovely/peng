package mldn.concurrent.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int y = 0; y < 100; y++) {
                    try{
                        TimeUnit.SECONDS.sleep(1);
                        String msg = Thread.currentThread().getName()+" 生产数据 = "+y;
                        queue.put(msg);
                        System.out.println("{生产数据}"+y);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"生产者"+i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true) {
                    try{
                        TimeUnit.SECONDS.sleep(1);
                        System.err.println(" 消费数据 "+queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
