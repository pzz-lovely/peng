package locks.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-21 11:09
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RecursionDemo {
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource(){
        lock.lock();
        System.out.println("已经对资源进行了处理");
        try{
            if(lock.getHoldCount() <5) {
                System.out.println(lock.getHoldCount());
                accessResource();
            }
        }finally {
            lock.unlock();
        }
    }
}
