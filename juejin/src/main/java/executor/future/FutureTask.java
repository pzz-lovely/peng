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
    // 真正执行r
    private Callable<T> task;

    /**
     * 任务执行的状态，0为开始，1正常完成，2异常完成
     */
    private AtomicInteger state = new AtomicInteger(NEW);

    private AtomicReference<Thread> caller = new AtomicReference<Thread>();

    private static final int NEW =  0;
    private static final int FINISH = 1;
    private static final int EXCEPTION = 2;
    /**
     * 任务执行的结果
     */
    private Object result;


    public FutureTask(Callable<T> task) {
        this.task = task;
    }

    @Override
    public T get() throws InterruptedException {
        int s = state.get();
        // 如果任务还未执行完成，判断当前线程是否要进入阻塞状态
        if (s == NEW) {
            // 标识调用者线程是否被标记过
            boolean marked = false;
            for (; ; ) {
                // 重新获取state的值
                s = state.get();
                // 如果state大于NEW说明完成了，跳出循环
                if (s > NEW) {
                    break;
                    // 此处必须把caller的CAS更新和park()方法分成两步处理，不能把park()放在CAS中更新
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
        // 如果状态不是NEW，说明执行过了，直接返回
        if (state.get() != NEW) {
            return;
        }
        try{
            // 执行任务
            T t = task.call();
            // CAS更新state的值为FINISH
            // 如果更新成功，就把r赋值给result
            // 如果更新失败，说明state的值不为NEW，也就是任务已经执行过了
            if (state.compareAndSet(NEW, FINISH)) {
                this.result = t;
                // finish
                finish();
            }
        } catch (Exception e) {
            // 如果CAS更新state的值为EXCEPTION成功，就e赋值个result
            // 如果CAS更新失败，说明state的值不为NEW了，也就是任务已经执行了
            if (state.compareAndSet(NEW, EXCEPTION)) {
                this.result = e;
                // finish()必须放在state里面，见下面的分析
                finish();
            }
        }
    }


    private void finish(){
        // 检查调用者是否为空，如果不为空，唤醒它
        // 调用者在调用get()方法进入阻塞状态
        for (Thread c; (c = caller.get()) != null; ) {
            if (caller.compareAndSet(c, null)) {
                LockSupport.unpark(c);
            }
        }
    }
}
