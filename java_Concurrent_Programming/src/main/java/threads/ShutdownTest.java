package threads;

import java.util.concurrent.TimeUnit;

/**
 * 安全得终止线程
 */
public class ShutdownTest {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread countThread = new Thread(runner, "CountThread");
        countThread.start();
        //睡眠一秒，main线程对CountThread进行中断，是CountThread能够感知到中断而结束的
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        //更换引用
        countThread = new Thread(two, "CountThread");
        countThread.start();    //启动线程
        //睡眠一秒,main线程对Runner two进行取消，是CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }
    private static class Runner implements Runnable{
        private long i ;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                //不是中断的线程
                i++;
            }
            System.out.println("Count i =" + i);
        }

        public void cancel(){
            on = false;
        }
    }
}
