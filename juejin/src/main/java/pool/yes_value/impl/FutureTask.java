package pool.yes_value.impl;

import pool.yes_value.FutureExecutor;
import pool.yes_value.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class FutureTask<T> implements Runnable,Task<T> {
    /**
     * ����������
     */
    private Callable<T> task;

    public FutureTask(Callable<T> call) {
        this.task = call;
    }


    private int NEW  = 0;
    private int FINISHED = 1;
    private int EXCEPTION =2 ;

    private AtomicInteger state = new AtomicInteger(NEW);
    // �������get()�߳�
    private AtomicReference<Thread> caller = new AtomicReference<>();

    // �����洢������
    private Object result;



    @Override
    public T get() {
        int s = state.get();

        if (s == NEW) {
            // ��ʾ�������߳��Ƿ񱻱�ǹ�
            boolean marked = false;
            for (; ; ) {
                s = state.get();

                // ���state����NEW˵������ˣ�����ѭ��
                if(s > NEW)
                    break;
                else if (!marked) {
                    marked = caller.compareAndSet(null, Thread.currentThread());
                }else {
                    LockSupport.park();
                }
            }
        }
        if(s == FINISHED)
            return (T)result;
        throw new RuntimeException((Throwable) result);
    }

    @Override
    public void run() {
        // ���״̬����NEW��˵���Լ�ִ�й��ˣ�ֱ�ӷ���
        if(state.get() != NEW)
            return;
        try{
            T t = task.call();
            // CAS����state��ֵΪFINISHED
            // ������³ɣ��Ͱ�r��ֵ��result
            // �������ʧ�ܣ�˵��state��ֵΪ��ΪNEW��Ҳ���������Լ�ִ�й���
            if (state.compareAndSet(NEW, FINISHED)) {
                this.result = t;

                finish();
            }
        } catch (Exception e) {
            // ���CAS����state����ΪEXCEPTIN�ɹ����Ͱ�e��ֵ��result
            // ���CAS����ʧ�ܣ�˵��state����ֵ��ΪNEW,Ҳ���������Լ�ִ�й���
            if (state.compareAndSet(NEW, EXCEPTION)) {
                this.result = e;
                finish();
            }
        }
    }


    private void finish(){
        // ���������Ƿ�Ϊ�գ������Ϊ�գ�������
        // �������ڵ���get()�����Ľ�������״̬
        for (Thread c; (c = caller
                .get()) != null; ) {
            if (caller.compareAndSet(c, null)) {

                LockSupport.unpark(c);
            }
        }
    }
}
