package pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static java.lang.System.currentTimeMillis;

/**
 * @Auther lovely
 * @Create 2020-08-24 9:58
 * @Description todo
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个定时线程池
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);

        System.out.println("start :" + currentTimeMillis());

        // 执行一个无返回值任务，5秒后执行，只执行一次
        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("spring: " + currentTimeMillis());
        }, 5, TimeUnit.SECONDS);

        // 执行一个有返回值任务，5秒后执行，只执行一次
        ScheduledFuture<String> schedule = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("inner summer" + currentTimeMillis());
            return "outer summer";
        }, 5, TimeUnit.SECONDS);

        System.out.println(schedule.get() + System.currentTimeMillis());

        // 按固定频率执行一个任务，每2秒执行一次，1秒后执行
        // 任务开始时的2秒后
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("autumn: " + System.currentTimeMillis());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }, 1, 2, TimeUnit.SECONDS);

        // 按固定延时执行一个任务，每延时2秒执行一次，1秒执行
        // 任务结束时的2秒后，本文由公从号“彤哥读源码”原创
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("winter: " + System.currentTimeMillis());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }, 1, 2, TimeUnit.SECONDS);
    }
}
