package executor;



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther lovely
 * @Create 2020-08-18 17:26
 * @Description todo
 */
public class MyThreadPoolExecutor implements Executor {

    // �߳�����
    private String name;

    // �����߳���
    private int coreSize;

    // ����߳���
    private int maxSize;

    // �������
    private BlockingQueue<Runnable> taskQueue;

    // �ܾ�����
    private RejectPolicy rejectPolicy;

    /**
     * ��ǰ�������е��߳���
     * ��Ҫ�޸��߳��¼�������֪������ʹ��AtomicInteger
     * ����volatile CAS������
     */
    private AtomicInteger runningCount;


    private AtomicInteger sequence = new AtomicInteger(0);

    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable task) {
        // �������е��߳���
        int count = runningCount.get();
        // ����������е��߳���С�ں����߳�����ֱ�Ӽ�һ���߳�
        if(count < coreSize){
            // ע�⣬���ﲢ��һ����ӳɹ���addWorker()�������滹Ҫ�ж�һ���ǲ���ȷʵС
            if(addWorker(task,true))
                return;
            // �����Ӻ����߳�ʧ�ܣ�����������߼�
        }
        // ����ﵽ�˺����߳������ȳ������������
        // ����֮����ʹ��offer()������Ϊ�����������offer()����������false
        if (taskQueue.offer(task)) {
            // do nothing
        }else {
            // ������ʧ�ܣ�˵���������ˣ��Ǿ����һ���Ǻ����߳�
            rejectPolicy.reject(task, this);
        }
    }


    private boolean addWorker(Runnable newTask, boolean core) {
        // �����ж��ǲ�����Ŀ��Դ���һ���߳�
        for (;;){
            // �������е��߳���
            int count = runningCount.get();
            // �����̻߳��ǷǺ����߳�
            int max = core ? coreSize : maxSize;
            // �����㴴���̵߳�������ֱ�ӷ���false
            if(count >= max)
                return false;
            // �޸�runningCount�ɹ������Դ����߳�
            if (runningCount.compareAndSet(count, count + 1)) {
                // �̵߳�����
                String threadName = (core ? "core_" : "") + name + sequence.incrementAndGet();
                // �����̲߳�����
                new Thread(() -> {
                    System.out.println("thread name:" + Thread.currentThread().getName());
                    // ��������
                    Runnable task = newTask;
                    // ���ϴ����������ȡ����ִ�У����ȡ����������Ϊnull��������ѭ�����߳�Ҳ��ֹ
                    while (task != null || (task = getTask()) != null) {
                        try{
                            // ִ������
                            task.run();
                        }finally{
                            // ����ִ����ϣ���Ϊ��
                            task = null;
                        }
                    }
                },threadName).start();
                break;
            }
        }
        return true;
    }


    private Runnable getTask(){
        try{
            // take()������һֱ����ֱ��ȡ����Ϊֹ
            return taskQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            // �߳��ж��ˣ�����null���Խ�����ǰ�߳�
            // ��ǰ�̶߳�Ҫ�����ˣ���ӦҪ��runningCount��������1
            runningCount.decrementAndGet();
            return null;
        }
    }


}
