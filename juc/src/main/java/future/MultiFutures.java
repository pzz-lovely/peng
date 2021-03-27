package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther lovely
 * @Create 2020-03-26 11:48
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示批量提交任务是，用List来批量接收结果
 */
public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(()->{
                Thread.sleep(3000);
                return new Random().nextInt();
            });
            futures.add(future);
        }

        futures.stream().forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });
    }
}
