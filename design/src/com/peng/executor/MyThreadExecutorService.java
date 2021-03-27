package com.peng.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lovely
 * @Create 2020-09-09 12:46
 * @Description todo
 */
public class MyThreadExecutorService implements ExecutorService {

    private int coreSize;
    private int maxSize;
    private BlockingQueue<Runnable> takeQueue;
    private RejectPolicy rejectPolicy;
    private String name;


    private AtomicInteger runCounter = new AtomicInteger(0);

    public MyThreadExecutorService(int coreSize, int maxSize, BlockingQueue<Runnable> takeQueue, RejectPolicy rejectPolicy, String name) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.takeQueue = takeQueue;
        this.rejectPolicy = rejectPolicy;
        this.name = name;
    }

    @Override
    public void execute(Runnable command) {
        // 首先判断 运行的线程数 小于 核心数
        int runCount = runCounter.get();

        if (runCount < coreSize) {
            // 直接添加一个核心线程
            if(addWorker(command, true))
                return;
        }

        // 看看 队列满了没
        if(takeQueue.offer(command)){

        }else{
            // 执行拒接策略
            if(!addWorker(command,false))
                rejectPolicy.reject(command, this);
        }
    }


    private boolean addWorker(Runnable task, boolean isCore) {
        for (; ; ) {
            int runCount = runCounter.get();

            String threadName = isCore ? "core" + runCounter.get(): "non core";
            int count = isCore ? coreSize : maxSize;
            if (runCount < count)
                return false;

            if (runCounter.compareAndSet(runCount, runCount + 1)) {
                new Thread(()->{
                    Runnable runTask = task;
                    while (runTask != null || (runTask = getTask()) != null) {
                        try{
                            runTask.run();
                        }finally {
                            runTask = null;
                        }
                    }
                },threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try{
            return takeQueue.take();
        } catch (InterruptedException e) {
            runCounter.decrementAndGet();
            e.printStackTrace();
            return null;
        }
    }
}