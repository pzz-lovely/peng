package juc;

import java.util.concurrent.locks.StampedLock;

class Point{
    private double x,y;
    private final StampedLock stampedLock = new StampedLock();

    void move(double deltaX, double deltaY) {
        //��ȡд��������һ���汾��
        long stamp = stampedLock.writeLock();
        try{
            x += deltaX;
            y += deltaY;
        }finally{
            //�ͷ�д������Ҫ���������ȡ�İ汾��
            stampedLock.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){
        //�ֹ���
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x,currentY = y;
        //��֤�汾���Ƿ��б仯
        if (!stampedLock.validate(stamp)) {
            //�汾�ű��ˣ��ֹ۶�ת���۶�
            stamp = stampedLock.readLock();
            try{
                //���¶�ȡx,y��ֵ
                currentX = x;
                currentY = y;
            }finally{
                //�ͷŶ�������Ҫ���������ȡ�İ汾��
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void moveIfAtOrigin(double newX, double newY) {
        //��ȡ���۶���
        long stamp = stampedLock.readLock();
        try{
            while (x == 0.0 && y == 0.0) {
                //ת��д��s
                long ws = stampedLock.tryConvertToWriteLock(stamp);
                //ת���ɹ�
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                }else {
                    //ת��ʧ��
                    stampedLock.unlockRead(stamp);
                    //��ȡд��
                    stamp = stampedLock.writeLock();
                }
            }
        }finally{
            //�ͷ���
            stampedLock.unlock(stamp);
        }
    }
}



public class StampedLockTest {

}
