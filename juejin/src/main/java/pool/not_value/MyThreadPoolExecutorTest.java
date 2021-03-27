package pool.not_value;

import pool.not_value.imp.DiscardRejectPolicy;
import pool.not_value.imp.MyThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;

public class MyThreadPoolExecutorTest {
    public static void main(String[] args) {
        MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor("0.0", 5, 100, new ArrayBlockingQueue<Runnable>(20), new DiscardRejectPolicy());
        for (int i = 0; i < 200; i++) {
            mtpe.execute(new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" i ");
            }));
        }
    }
}
