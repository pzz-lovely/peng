package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �߳� �������� ״̬ ����
 */
public class ThreadLifeTest {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();  //����
        new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println(" synchronized1 wait");
                    //object.wait();
                    object.wait(5000);
                    System.out.println(" synchronized1 after waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "synchronizedThread1").start();

        new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("synchronized2 notify");
                    //�򿪻�ر���Σ��۲�synchronized1��״̬
                    object.notify();
                    //notify֮��ǰ�̲߳�����ʩ������ֻ�Ǳ�notify���̴߳ӵȴ����н���ͬ������
                    //sleepҲ�����ͷ���
                    Thread.sleep(5000);
                    System.out.println("synchronized2 destroy");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "synchronizedThread2").start();

        new Thread(() -> {
            lock.lock();
            System.out.println(" ReentrantLock1 waiting");
            try {
                //condition.await();
                condition.await(5, TimeUnit.SECONDS);
                System.out.println(" ReentrantLock1 after waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "ReentrantLock_Condition1").start();

        new Thread(()->{
            try {
                lock.lock();
                System.out.println("ReentrantLock2 signal");
                //�򿪻�ر���Σ��۲�ReentrantLock1��״̬
                condition.signal();
                // signal֮��ǰ�̲߳������ͷ�����ֻ�Ǳ�signal���̴߳ӵȴ����н���ͬ������
                // sleepҲ�����ͷ���
                Thread.sleep(5000);
                System.out.println("ReentrantLock2 destroy");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"ReentrantLock_Condition2").start();

        Thread.sleep(10000);
        //��ȡJava�̹߳���MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }

        /**
         * ��һ�ν�� ��object.notify()�� condition.signal()ע�͵�
         *      ReentrantLock_Condition2��synchronizedThread2 ���������߳���
         *         [14]ReentrantLock_Condition1
         *         [12]synchronizedThread1 �����ڵȴ�״̬���ȴ� notify()��signal()
         * �ڶ��ν�� ��object.notify() �� condition.signal()ȡ��ע��
         *      object.notify() �� condition.signal() �� ReentrantLock_Condition1��synchronizedThread1 �ӵȴ�״̬���ѵ��� ���� �����̺߳� ReentrantLock_Condition1��synchronizedThread1����������״̬
         *      ReentrantLock2 �� synchronized2 ������� �Ų�������
         * �����ν��: ���ڶ��ν������
         */
    }
}
