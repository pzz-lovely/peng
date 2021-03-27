package future;

import java.util.concurrent.*;

/**
 * @Auther lovely
 * @Create 2020-08-18 11:30
 * @Description todo
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future = executor.submit(() -> {
            return 1;
        });
        System.out.println(future.get());

    }
}
