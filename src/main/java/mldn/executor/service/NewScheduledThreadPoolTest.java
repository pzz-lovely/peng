package mldn.executor.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolTest {
    public static void main(String[] args) {
        //创建定时调度池，并且设置允许的内核线程数量为1
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 10; i++) {
            int index = i;
            //设置调度任务，操作单位为"秒",3秒后开始执行，每2秒执行一次
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " x " + index);
                }
            }, 3, 2, TimeUnit.SECONDS);
        }
    }
}
