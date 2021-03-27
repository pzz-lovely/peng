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
     * 真正的任务
     */
    private Callable<T> task;

    public FutureTask(Callable<T> call) {
        this.task = call;
    }


    private int NEW  = 0;
    private int FINISHED = 1;
    private int EXCEPTION =2 ;

    private AtomicInteger state = new AtomicInteger(NEW);
    // 保存调用get()线程
    private AtomicReference<Thread> caller = new AtomicReference<>();

    // 真正存储的数据
    private Object result;



    @Override
    public T get() {
        int s = state.get();

        if (s == NEW) {
            // 表示调用这线程是否被标记郭
            boolean marked = false;
            for (; ; ) {
                s = state.get();

                // 如果state大于NEW说明完成了，跳出循环
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
        // 如果状态不是NEW，说明以及执行过了，直接返回
        if(state.get() != NEW)
            return;
        try{
            T t = task.call();
            // CAS更新state的值为FINISHED
            // 如果更新成，就把r赋值给result
            // 如果更新失败，说明state的值为不为NEW，也就是任务以及执行过了
            if (state.compareAndSet(NEW, FINISHED)) {
                this.result = t;

                finish();
            }
        } catch (Exception e) {
            // 如果CAS更新state的中为EXCEPTIN成功，就把e赋值给result
            // 如果CAS更新失败，说明state的中值不为NEW,也就是任务以及执行过了
            if (state.compareAndSet(NEW, EXCEPTION)) {
                this.result = e;
                finish();
            }
        }
    }


    private void finish(){
        // 检查调用者是否为空，如果不为空，唤醒它
        // 调用这在调用get()方法的进入阻塞状态
        for (Thread c; (c = caller
                .get()) != null; ) {
            if (caller.compareAndSet(c, null)) {

                LockSupport.unpark(c);
            }
        }
    }
}
