package mldn.executor.service;

import java.util.concurrent.*;

class ThreadItem implements Callable<String> {
    @Override
    public String call() {
        long timeMillis = System.currentTimeMillis();
        try {
            System.out.println("start " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("End " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " : " + timeMillis;
    }
}

public class NewCachedThreadPoolTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
        //创建一个异步处理任务，并且该异步任务需要接收一个线程实例
        CompletionService<String> completion = new ExecutorCompletionService<>(service);
        for (int i = 0; i < 10; i++) {
            completion.submit(new ThreadItem());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("获取数据 : "+completion.take().get());
        }
        service.shutdown();
    }
}
