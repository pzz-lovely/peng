package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-19 15:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        //过几秒执行任务
        threadPool.schedule(new Task(), 5, TimeUnit.SECONDS);

        //以一定频率重复运行
        threadPool.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}
