package juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class ThreadPoolDemo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"\t"+i);
        }
    }
}

class ThreadPoolDemo2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0 ;
        for (int i = 0; i < 100; i++) {
            sum+=i;
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
        return sum;
    }
}

public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(new ThreadPoolDemo2()));
        }
        for (Future<Integer> result : results) {
            System.out.println(result.get());
        }
        pool.shutdown();
    }
}
