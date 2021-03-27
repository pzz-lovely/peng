package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口，可以获取线程执行的几个，futureTask实际上实现了Runnable接口
 */
public class CreatingThread implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getId() + "is running");
        return Thread.currentThread().getId();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Long> task = new FutureTask<>(new CreatingThread());
        new Thread(task).start();
        System.out.println("等待call任务完成");
        Long result = task.get();
        System.out.println("任务结束:" + result);
    }
}

