package threadcoreknowledge;

/**
 * @Auther lovely
 * @Create 2020-05-22 19:48
 * @Description todo
 */
public class ThreadState {

    private static final Object waitObj = new Object();
    private static final Object blockObj = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread waitingThread = new Thread(new Waiting(), "WaitingThread");
        Thread sleepThread = new Thread(new Sleep(), "SleepThread");

        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        Thread blockThread1 = new Thread(new Blocked(), "BlockedThread1");
        Thread blockThread2 = new Thread(new Blocked(), "BlockedThread2");


        // 启动线程
        waitingThread.start();
        sleepThread.start();
        blockThread1.start();
        blockThread2.start();

        // 打印各自的线程状态
        System.out.println("WaitingThread="+waitingThread.getState());
        System.out.println("SleepThread="+sleepThread.getState());
        System.out.println("BlockedThread1="+blockThread1.getState());
        System.out.println("BlockedThread2="+blockThread2.getState());

    }

    // 该线程不断的睡眠
    static class Sleep implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //该线程在 调用了wait方法进行永久等待
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true) {
                synchronized (waitObj) {
                    try{
                        waitObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 该线程在 没有获取到锁时，会出现阻塞状态
    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (blockObj) {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


