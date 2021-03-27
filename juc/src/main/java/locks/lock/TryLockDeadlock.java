package locks.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-21 9:20
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TryLockDeadlock implements Runnable {
    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();


    public static void main(String[] args) {
        TryLockDeadlock r1 = new TryLockDeadlock();
        TryLockDeadlock r2 = new TryLockDeadlock();
        r1.flag=1;
        r2.flag=0;
        new Thread(r1,"线程1").start();
        new Thread(r2,"线程2").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                if (getLock(lock1, lock2, "锁1", "锁2")) {
                    break;
                }
            }else if(flag==0) {
                if (getLock(lock2, lock1, "锁2", "锁1")) {
                    break;
                }
            }
        }
    }


    private boolean getLock(Lock lock1,Lock lock2,String lockName1,String lockName2){
        String currentName = Thread.currentThread().getName();
        try {
            if (lock1.tryLock(8000, TimeUnit.MILLISECONDS)) {
                try{
                    System.out.println(currentName+"获取到了"+lockName1);
                    Thread.sleep(new Random().nextInt(1000));
                    if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try{
                            System.out.println(currentName+"获取到了"+lockName2);
                            System.out.println(currentName+"获取到了两把锁");
                            return true;
                        }finally {
                            lock2.unlock();
                        }
                    }else{
                        System.out.println(currentName+"获取"+lockName2+"失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock1.unlock();
                    Thread.sleep(new Random().nextInt(1000));
                }
            }else{
                System.out.println(currentName+"获取"+lockName1+"失败，重试");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
