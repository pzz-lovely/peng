package stamp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

class Account{
    private String name;
    private double asset;
    private StampedLock stampedLock = new StampedLock();

    public Account(String name, double asset) {
        this.name = name;
        this.asset = asset;
    }

    public void saveMoney(double money){
        long stamp = this.stampedLock.readLock();   //获取读锁，检查状态
        boolean flag = true;
        try{
            long writeStamp = this.stampedLock.tryConvertToWriteLock(stamp);    //转为写锁
            while (flag) {
                if (writeStamp != 0) {
                    stamp = writeStamp;//修改为写的标记
                    this.asset += money;
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"修改金额"+money+" 当前资产 "+asset);

                }else {
                    this.stampedLock.unlockRead(stamp);           //释放读锁\
                    writeStamp = stampedLock.writeLock();
                    stamp = writeStamp;

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            stampedLock.unlock(stamp);
        }
    }

    @Override
    public String toString() {
        long stamp = stampedLock.tryReadLock();
        try{
            double current = this.asset;
            TimeUnit.SECONDS.sleep(1);
            if (!this.stampedLock.validate(stamp) || (stamp & (long) (Math.pow(2, 7) - 1)) == 0) {
                long readStamp = this.stampedLock.readLock();
                current = this.asset;
                stamp = readStamp;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            try{
                this.stampedLock.unlockRead(stamp);
            }catch (Exception e){

            }
        }
        return null;
    }
}

public class StampTest1 {
    public static void main(String[] args) {

    }
}
