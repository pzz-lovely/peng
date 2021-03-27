package one.chatpter14.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-02-25 17:05
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Bank {
    private final double[] accounts;
    private Lock lock ;
    private Condition sufficientFunds;
    /**
     * Constructs the bank
     * @param n
     * @param initialBalance
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        lock = new ReentrantLock();
        sufficientFunds = lock.newCondition();
    }

    /**
     * Transfers money from one account to another
     * @param from the account to transfer from  Ҫת�˵��˻�
     * @param to the account to transfer to Ҫת�����˻�
     * @param amount the amount to transfer ת��Ǯ��
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        try{
            lock.lock();
            /*
             * ת�˵��û��ж��Ƿ���С��ת��Ǯ����С�ھ��õ�ǰ�߳� ���еȴ���
             *   ���û�������߳���������һ���ȴ����̣߳�������Զ�������У�Ȼ��������deadlock
             */
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread().getName());
            accounts[from] -= amount;
            System.out.printf("%10.2 from %d to %d ", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance : %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public double getTotalBalance(){
        double sum = 0;
        for(double a :accounts)
            sum += a;
        return sum;
    }

    public int size(){
        return accounts.length;
    }
}
