package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Auther lovely
 * @Create 2020-03-09 9:51
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 */
public class WaitNotifyPrintOddEventSyn {
    private static int count;
    private static final Object lock = new Object();
    //新建两个线程
    //1个只处理偶数，第二分只处理奇数(用位运算)
    //用synchronized来通信
    public static void main(String[] args) {
        new Thread(() -> {
            for (count = 0; count < 100; count++) {
                synchronized (lock) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                    }
                }
            }
        }, "偶数");
        new Thread(() -> {
            for (count = 0; count < 100; count++) {
                synchronized (lock) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                    }
                }
            }
        }, "奇数");
    }
}
