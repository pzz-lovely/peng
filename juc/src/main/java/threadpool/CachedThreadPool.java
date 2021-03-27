package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-19 15:48
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.submit(new Task());
        }
    }
}
