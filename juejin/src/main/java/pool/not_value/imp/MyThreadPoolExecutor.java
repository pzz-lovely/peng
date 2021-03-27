package pool.not_value.imp;

import pool.not_value.Executor;
import pool.not_value.RejectPolicy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 手写一个线程池
 */
public class MyThreadPoolExecutor implements Executor {
    private String name;
    private AtomicInteger sequence = new AtomicInteger(0);  //线程序列号
    private int coreSize;   //核心线程数
    private int maxSize;    //最大线程数
    private BlockingQueue<Runnable> taskQueue;//任务队列
    private RejectPolicy rejectPolicy;  //拒接策略

    /**
     * 当前正在运行的线程数
     * 需要修改时间线程立即感知，所有使用AtomicInteger
     * 获取这也可以使用volatile并结合Unsafe做cas操作
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
        //正在运行的线程数
        int count = runningCount.get(); //获取值
        //如果正在运行的线程数小于核心线程数，直接加一个线程
        if (count < coreSize) {//正在运行的线程数 小于 核心线程数
            //注意这里不一定添加成功，addWorker()方法里面判断一次是不是确实小
            if (addWorker(command,true)) {
                return;
            }
        }
        //尝试让队伍入队 当前正在运行的线程数大于 核心线程
        //offer() 队列满了会返回false
        if (taskQueue.offer(command)) {
            //
        }else{
            //入队失败,说明队列满了，那就添加一个非核心线程
            if (!addWorker(command, false)) {
                //如果添加非核心线程失败，那就执行拒绝策略
                rejectPolicy.reject(command, this);
            }
        }
    }

    /**
     * 添加任务到队列中
     * @param newTask
     * @param core 是否是核心线程
     * @return
     */
    private boolean addWorker(Runnable newTask, boolean core) {
        //自旋判断是不是真的可以创建一个线程
        for (; ; ) {
            //正在运行的线程数
            int count = runningCount.get();
            //核心线程还是非核心线程
            int max = core ? coreSize : maxSize;
            //不满足条件直接返回false
            if (count >= max) { // 线程数大于 核心和非核心线程数了
                return false;
            }
            //修改runningCount成功，可以创建线程
            if(runningCount.compareAndSet(count,count+1)){
                //线程的名称
                String threadName = (core ? "core核心" : "非核心") + name + sequence.incrementAndGet();  //自增并获取
                new Thread(()->{
                    System.out.println("addWorker里线程启动");
                    Runnable task = newTask;
                    //不断从任务队列中取任务执行，如果取出来的任务为null,则跳出循环，线程也就结束了
                    //首先判断newTask是否为null 不为null即调用 newTask方法
                    //如果为null，就从阻塞队列中获取
                    while (task != null || (task = getTask()) != null) {
                        try{
                            //执行任务
                            task.run();
                        }finally {
                            //任务执行完成置为空
                            //如果这里不为null 保存在阻塞队列中的 任务就执行不了
                            task = null;
                            // runningCount.decrementAndGet();
                        }
                    }
                },threadName).start();
                break;
            }
        }
        //自旋完成任务
        return true;
    }

    public Runnable getTask() {
        try {
            return taskQueue.take();
        } catch (InterruptedException e) {
            //线程中断了，返回Null结束当前线程
            //把runningCount的数量减一
            runningCount.decrementAndGet();
            return null;
        }
    }
}
