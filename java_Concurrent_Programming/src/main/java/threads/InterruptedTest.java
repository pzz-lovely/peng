package threads;

/**
 * 中断可以理解为线程的一个标识位属性，它标识一个运行中的线程是否被其他线程进行了中断操作
 * 线程通过检查自身是否被中断来进行相应，线程通过反复isInterrupted()来进行判断是否被中断，也可以调用静态方法Thread.interrupted()对当前线程的中断标记进行复位。如果该线程已经处于终结状态，即使该线程被中断过，在调用该线程对象的isInterrupted()是依旧会返回false
 */
public class InterruptedTest {
    public static void main(String[] args) {
        //sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        //busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThead");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //休眠5秒,让sleepThread和busyThread充分运行
        SleepUtils.second(10);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        //防止sleepThread和busyThread立即退出
        SleepUtils.second(2);

    }
    //中断表示位被清除了
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true) {
                System.out.println("睡觉喔");
                SleepUtils.second(5);
            }
        }
    }
    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true) {
                //System.out.println("很忙");
            }
        }
    }
}
