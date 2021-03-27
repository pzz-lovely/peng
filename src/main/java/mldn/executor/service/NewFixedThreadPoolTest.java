package mldn.executor.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class NewFixedThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Set<Callable<String>> allThread = new HashSet<>();  //保存多个线程对象
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            allThread.add(()->{
                return Thread.currentThread().getId()+" - "+Thread.currentThread().getName()+" 数量 "+temp;
            });
        }
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Future<String>> result = service.invokeAll(allThread);
        for (Future future : result) {
            System.out.println(future.get());
        }
    }
}
