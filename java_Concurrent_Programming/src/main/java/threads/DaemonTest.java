package threads;

/**
 * Daemon线程是一种支持性线程，因为他主要被用作程序中后台调度以及支持性工作。这意味着，当一个java虚拟机不存在非daemon线程的时候，java虚拟机将退出。可以通过调用Thread.setDaemon(true)将线程设置为daemon线程
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
    }
    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally{
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
