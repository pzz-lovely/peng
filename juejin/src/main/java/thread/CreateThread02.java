package thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器 java.util.Timer
 * 使用定时器java.util.Timer可以快速地实现定时任务，timerTask实际上实现了Runnable接口
 */
public class CreateThread02 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //每隔一秒执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        },1000,2000);
    }
}
