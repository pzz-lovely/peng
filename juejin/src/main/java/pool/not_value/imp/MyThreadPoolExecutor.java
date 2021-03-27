package pool.not_value.imp;

import pool.not_value.Executor;
import pool.not_value.RejectPolicy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * ��дһ���̳߳�
 */
public class MyThreadPoolExecutor implements Executor {
    private String name;
    private AtomicInteger sequence = new AtomicInteger(0);  //�߳����к�
    private int coreSize;   //�����߳���
    private int maxSize;    //����߳���
    private BlockingQueue<Runnable> taskQueue;//�������
    private RejectPolicy rejectPolicy;  //�ܽӲ���

    /**
     * ��ǰ�������е��߳���
     * ��Ҫ�޸�ʱ���߳�������֪������ʹ��AtomicInteger
     * ��ȡ��Ҳ����ʹ��volatile�����Unsafe��cas����
     */
    private AtomicInteger runningCount = new AtomicInteger(0);


    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }


    @Override
    public void execute(Runnable command) {
        //�������е��߳���
        int count = runningCount.get(); //��ȡֵ
        //����������е��߳���С�ں����߳�����ֱ�Ӽ�һ���߳�
        if (count < coreSize) {//�������е��߳��� С�� �����߳���
            //ע�����ﲻһ����ӳɹ���addWorker()���������ж�һ���ǲ���ȷʵС
            if (addWorker(command,true)) {
                return;
            }
        }
        //�����ö������ ��ǰ�������е��߳������� �����߳�
        //offer() �������˻᷵��false
        if (taskQueue.offer(command)) {
            //
        }else{
            //���ʧ��,˵���������ˣ��Ǿ����һ���Ǻ����߳�
            if (!addWorker(command, false)) {
                //�����ӷǺ����߳�ʧ�ܣ��Ǿ�ִ�оܾ�����
                rejectPolicy.reject(command, this);
            }
        }
    }

    /**
     * ������񵽶�����
     * @param newTask
     * @param core �Ƿ��Ǻ����߳�
     * @return
     */
    private boolean addWorker(Runnable newTask, boolean core) {
        //�����ж��ǲ�����Ŀ��Դ���һ���߳�
        for (; ; ) {
            //�������е��߳���
            int count = runningCount.get();
            //�����̻߳��ǷǺ����߳�
            int max = core ? coreSize : maxSize;
            //����������ֱ�ӷ���false
            if (count >= max) { // �߳������� ���ĺͷǺ����߳�����
                return false;
            }
            //�޸�runningCount�ɹ������Դ����߳�
            if(runningCount.compareAndSet(count,count+1)){
                //�̵߳�����
                String threadName = (core ? "core����" : "�Ǻ���") + name + sequence.incrementAndGet();  //��������ȡ
                new Thread(()->{
                    System.out.println("addWorker���߳�����");
                    Runnable task = newTask;
                    //���ϴ����������ȡ����ִ�У����ȡ����������Ϊnull,������ѭ�����߳�Ҳ�ͽ�����
                    //�����ж�newTask�Ƿ�Ϊnull ��Ϊnull������ newTask����
                    //���Ϊnull���ʹ����������л�ȡ
                    while (task != null || (task = getTask()) != null) {
                        try{
                            //ִ������
                            task.run();
                        }finally {
                            //����ִ�������Ϊ��
                            //������ﲻΪnull ���������������е� �����ִ�в���
                            task = null;
                            // runningCount.decrementAndGet();
                        }
                    }
                },threadName).start();
                break;
            }
        }
        //�����������
        return true;
    }

    public Runnable getTask() {
        try {
            return taskQueue.take();
        } catch (InterruptedException e) {
            //�߳��ж��ˣ�����Null������ǰ�߳�
            //��runningCount��������һ
            runningCount.decrementAndGet();
            return null;
        }
    }
}
