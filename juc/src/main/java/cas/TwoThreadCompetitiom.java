package cas;

/**
 * @Auther lovely
 * @Create 2020-03-23 9:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 模拟CAS操作，等价代码
 */
public class TwoThreadCompetitiom implements Runnable {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompetitiom r = new TwoThreadCompetitiom();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}
