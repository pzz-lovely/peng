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
        // ����һ����ʱ�̳߳�
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);

        System.out.println("start :" + currentTimeMillis());

        // ִ��һ���޷���ֵ����5���ִ�У�ִֻ��һ��
        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("spring: " + currentTimeMillis());
        }, 5, TimeUnit.SECONDS);

        // ִ��һ���з���ֵ����5���ִ�У�ִֻ��һ��
        ScheduledFuture<String> schedule = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("inner summer" + currentTimeMillis());
            return "outer summer";
        }, 5, TimeUnit.SECONDS);

        System.out.println(schedule.get() + System.currentTimeMillis());

        // ���̶�Ƶ��ִ��һ������ÿ2��ִ��һ�Σ�1���ִ��
        // ����ʼʱ��2���
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("autumn: " + System.currentTimeMillis());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }, 1, 2, TimeUnit.SECONDS);

        // ���̶���ʱִ��һ������ÿ��ʱ2��ִ��һ�Σ�1��ִ��
        // �������ʱ��2��󣬱����ɹ��Ӻš�ͮ���Դ�롱ԭ��
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("winter: " + System.currentTimeMillis());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }, 1, 2, TimeUnit.SECONDS);
    }
}
