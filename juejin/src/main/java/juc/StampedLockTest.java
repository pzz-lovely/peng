package juc;

import java.util.concurrent.locks.StampedLock;

class Point{
    private double x,y;
    private final StampedLock stampedLock = new StampedLock();

    void move(double deltaX, double deltaY) {
        //获取写锁，返回一个版本号
        long stamp = stampedLock.writeLock();
        try{
            x += deltaX;
            y += deltaY;
        }finally{
            //释放写锁，需要传入上面获取的版本号
            stampedLock.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){
        //乐观锁
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x,currentY = y;
        //验证版本号是否有变化
        if (!stampedLock.validate(stamp)) {
            //版本号变了，乐观读转悲观读
            stamp = stampedLock.readLock();
            try{
                //重新读取x,y的值
                currentX = x;
                currentY = y;
            }finally{
                //释放读锁，需要传入上面获取的版本号
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void moveIfAtOrigin(double newX, double newY) {
        //获取悲观读锁
        long stamp = stampedLock.readLock();
        try{
            while (x == 0.0 && y == 0.0) {
                //转换写锁s
                long ws = stampedLock.tryConvertToWriteLock(stamp);
                //转换成功
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                }else {
                    //转换失败
                    stampedLock.unlockRead(stamp);
                    //获取写锁
                    stamp = stampedLock.writeLock();
                }
            }
        }finally{
            //释放锁
            stampedLock.unlock(stamp);
        }
    }
}



public class StampedLockTest {

}
