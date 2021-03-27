package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Auther lovely
 * @Create 2020-03-26 11:34
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示一个future的使用方法
 */
public class OneFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(new CallableTask());
        /*Future<Integer> future = service.submit(new CallableTask());*/
        Future<Integer> future = service.submit(() -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        });
        System.out.println(future.get());
    }


    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
