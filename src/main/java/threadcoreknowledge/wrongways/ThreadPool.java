package threadcoreknowledge.wrongways;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-06 16:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 线程池创建线程的方法
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.submit(()->{
                System.out.println("ThreadName" + Thread.currentThread());
            });
        }
    }
}
