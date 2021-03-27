package reentrants.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest1 {
    private ReentrantLock sync = new ReentrantLock(true);
    public void lock(){
        //调用的sync属性的lock方法
        //这里sync是公平锁，所有是FairSync的实例
        sync.lock();
    }

   /* final void lock(){
        //调用AQS的acquire()方法获取锁

    }*/

    public final void acquire(int arg) {
        //尝试获取锁
        //如果失败了，就排队

    }

}
