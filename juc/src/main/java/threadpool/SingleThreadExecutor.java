package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-19 15:41
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            service.execute(new Task());
        }
    }
}
