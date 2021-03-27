package cas;

/**
 * @Auther lovely
 * @Create 2020-03-23 9:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 模拟CAS操作，等价代码
 */
public class SimulatedCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
