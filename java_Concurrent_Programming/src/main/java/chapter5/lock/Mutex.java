package chapter5.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ��ռ��
 *  Sync �̳��� AbstractQueuedAsynchronizer
 * ��дtryAcquire(int arg)
 * tryRelease(int release)
 * isHeldExclusively() //�Ƿ���ռ��״̬
 */
public class Mutex implements Lock {
    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1; //״̬Ϊ1���� ��ȡ��
        }

        @Override
        protected boolean tryAcquire(int arg) {
            //���³ɹ� ���ȡ������
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(arg);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private static final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.tryRelease(0);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
}
