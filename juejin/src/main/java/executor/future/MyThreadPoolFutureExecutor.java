package executor.future;

import executor.MyThreadPoolExecutor;
import executor.RejectPolicy;
import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Auther lovely
 * @Create 2020-08-19 14:34
 * @Description todo
 */
public class MyThreadPoolFutureExecutor<T> extends MyThreadPoolExecutor implements FutureExecutor<T> {


    public MyThreadPoolFutureExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        super(name, coreSize, maxSize, taskQueue, rejectPolicy);
    }

    @Override
    public Future<T> submit(Callable<T> task) {
        // ��װ�ɽ�����ȡ����ֵ������
        FutureTask<T> futureTask = new FutureTask<>(task);

        // ����ʹ��ԭ����ִ������
        execute(futureTask);

        // ���ؽ���������ֻ��Ҫ������get����ֵ����������
        // �������ﷵ�ص���Future������FutureTask����
        return futureTask;
    }
}
