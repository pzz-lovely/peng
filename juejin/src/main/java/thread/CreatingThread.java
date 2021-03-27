package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ʵ��Callable�ӿڣ����Ի�ȡ�߳�ִ�еļ�����futureTaskʵ����ʵ����Runnable�ӿ�
 */
public class CreatingThread implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getId() + "is running");
        return Thread.currentThread().getId();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Long> task = new FutureTask<>(new CreatingThread());
        new Thread(task).start();
        System.out.println("�ȴ�call�������");
        Long result = task.get();
        System.out.println("�������:" + result);
    }
}

