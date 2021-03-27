package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Auther lovely
 * @Create 2020-03-09 9:01
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 每个锁都是独立的
 * 证明wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    static class MonitorThread1 extends Thread{
        @Override
        public void run() {
            synchronized (resourceA) {
                System.out.println("ThreadA got resourceA lock.");
                synchronized (resourceB) {
                    System.out.println("ThreadA got resourceB lock.");
                    try {
                        resourceA.wait();
                        System.out.println("Thread releases resourceA lock.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class MonitorThread2 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("ThreadB got resourceA lock.");
                synchronized (resourceB) {
                    System.out.println("ThreadB resourceB lock.");
                }
            }
        }
    }

    public static void main(String[] args) {
        MonitorThread1 monitorThread1 = new MonitorThread1();
        MonitorThread2 monitorThread2 = new MonitorThread2();
        monitorThread1.start();
        monitorThread2.start();
    }
}
