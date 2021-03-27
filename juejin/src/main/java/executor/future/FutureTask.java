package executor.future;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @Auther lovely
 * @Create 2020-08-19 14:35
 * @Description todo
 */
public class FutureTask<T> implements Runnable,Future<T>  {
    // ����ִ��r
    private Callable<T> task;

    /**
     * ����ִ�е�״̬��0Ϊ��ʼ��1������ɣ�2�쳣���
     */
    private AtomicInteger state = new AtomicInteger(NEW);

    private AtomicReference<Thread> caller = new AtomicReference<Thread>();

    private static final int NEW =  0;
    private static final int FINISH = 1;
    private static final int EXCEPTION = 2;
    /**
     * ����ִ�еĽ��
     */
    private Object result;


    public FutureTask(Callable<T> task) {
        this.task = task;
    }

    @Override
    public T get() throws InterruptedException {
        int s = state.get();
        // �������δִ����ɣ��жϵ�ǰ�߳��Ƿ�Ҫ��������״̬
        if (s == NEW) {
            // ��ʶ�������߳��Ƿ񱻱�ǹ�
            boolean marked = false;
            for (; ; ) {
                // ���»�ȡstate��ֵ
                s = state.get();
                // ���state����NEW˵������ˣ�����ѭ��
                if (s > NEW) {
                    break;
                    // �˴������caller��CAS���º�park()�����ֳ������������ܰ�park()����CAS�и���
                } else if (!marked) {
                    marked = caller.compareAndSet(null, Thread.currentThread());
                }else{
                    LockSupport.park();
                }
            }
        }
        if(s== FINISH)
            return (T) result;
        throw new RuntimeException((Throwable) result);
    }

    @Override
    public void run() {
        // ���״̬����NEW��˵��ִ�й��ˣ�ֱ�ӷ���
        if (state.get() != NEW) {
            return;
        }
        try{
            // ִ������
            T t = task.call();
            // CAS����state��ֵΪFINISH
            // ������³ɹ����Ͱ�r��ֵ��result
            // �������ʧ�ܣ�˵��state��ֵ��ΪNEW��Ҳ���������Ѿ�ִ�й���
            if (state.compareAndSet(NEW, FINISH)) {
                this.result = t;
                // finish
                finish();
            }
        } catch (Exception e) {
            // ���CAS����state��ֵΪEXCEPTION�ɹ�����e��ֵ��result
            // ���CAS����ʧ�ܣ�˵��state��ֵ��ΪNEW�ˣ�Ҳ���������Ѿ�ִ����
            if (state.compareAndSet(NEW, EXCEPTION)) {
                this.result = e;
                // finish()�������state���棬������ķ���
                finish();
            }
        }
    }


    private void finish(){
        // ���������Ƿ�Ϊ�գ������Ϊ�գ�������
        // �������ڵ���get()������������״̬
        for (Thread c; (c = caller.get()) != null; ) {
            if (caller.compareAndSet(c, null)) {
                LockSupport.unpark(c);
            }
        }
    }
}
