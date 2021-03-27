package locks.lock;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther lovely
 * @Create 2020-03-21 10:33
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 悲观锁 和 乐观锁
 */
public class PessimismOptimismLock {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    //悲观锁
    public synchronized void testMethod() {
    }


}
