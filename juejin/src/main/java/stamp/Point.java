package stamp;

import java.util.concurrent.locks.StampedLock;

/**
 * @Auther lovely
 * @Create 2020-01-12 10:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description Point
 */

public class Point {
    private double x, y;
    private final StampedLock stampedLock = new StampedLock();


    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try{
            //修改数据
            x += deltaX;
            y += deltaY;
        }finally{
            //释放锁
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     *
     * @return
     */
    public double distanceFromOrigin(){
        //乐观  读锁
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;
        //验证版本是否有变化
        if (!stampedLock.validate(stamp)) {
            //版本号变了，乐观锁转悲观锁
            stamp = stampedLock.readLock();
            try{
                currentX = x;
                currentY = y;
            }finally{
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }


    public void moveIfAtOrigin(double newX, double newY) {
        //获取悲观读锁
        long stamp = stampedLock.readLock();
        try{
            while (x == 0.0 && y == 0.0) {
                //转换为写锁
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
            stampedLock.unlock(stamp);
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
