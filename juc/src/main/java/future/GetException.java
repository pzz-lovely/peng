package future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther lovely
 * @Create 2020-03-26 13:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机。知道我们get时才抛出
 */
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final int no = i;
            Future<Integer> future = service.submit(()->{
                if (no % 2 == 0) {
                    throw new IllegalAccessException("非法参数");
                }
                return new Random().nextInt();
            });
            futures.add(future);
        }


        futures.stream().forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException异常");
            } catch (ExecutionException ex) {
                System.out.println("ExecutionException异常");
            }
        });
    }
}
