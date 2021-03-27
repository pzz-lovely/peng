package stamp;

import java.util.concurrent.locks.StampedLock;

/**
 * @Auther lovely
 * @Create 2020-01-12 10:18
 * @PACKAGE_NAME peng
 * @Description StampedLock是java中新增的类，它是一个更高效的读写锁的实现，而不是基于AQS来实现的，它的内部自成一片逻辑
 *  StampedReadWriteLock的读和写都是一种悲观锁的体现，StampedLock加入了一种新的模式--乐观锁，它是指当乐观锁时假定没有其他线程修改的数据，读取完成后再检查下版本号有没有变化，没有变化就读取成功了。
 */
public class StampedLockTest {
    public static void main(String[] args) {
        Point point = new Point();
        point.move(1.0, 2.0);
        System.out.println(point.getX() + " " + point.getY());
    }
}
