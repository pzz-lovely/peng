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
     * @param from the account to transfer from  要转账的账户
     * @param to the account to transfer to 要转到的账户
     * @param amount the amount to transfer 转的钱数
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        try{
            lock.lock();
            /*
             * 转账的用户判断是否是小于转的钱数，小于就让当前线程 进行等待。
             *   如果没有其他线程来唤醒这一个等待的线程，他就永远不在运行，然后导致死锁deadlock
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
