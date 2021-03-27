package threads.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;                //账号
    private Lock bankLock;
    private Condition shufficientFunds;
    /**
     * Constructs the bank  构建银行
     * @param n 银行的大小 n the number of accounts
     * @param initialBalance initialBalance the initial balance for each accounts
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);      //填充数组
        bankLock = new ReentrantLock();
        shufficientFunds = bankLock.newCondition(); //创建与该锁相关的条件对象
    }

    /**
     * Transfers money from one account to another 将钱从一个帐户转移到另一个帐户
     * @param from form the account to transfer from 要转移
     * @param to to the account to transfer to 转移到
     * @param amount amount the amount to transfer 金额
     */
    public void transfer(int from, int to, double amount) {
        bankLock.lock();            //获取锁
        try {
            while (accounts[from] < amount)
                shufficientFunds.await();
            if (accounts[from] < amount) return;
            System.out.println(Thread.currentThread().getName());
            accounts[from] -= amount;
            System.out.printf(" %10.f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total Balance : %10.2f %n", getTotalBalance());
            shufficientFunds.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bankLock.unlock();          //释放锁
        }
    }

    /**
     * Gets the sum of all counts balances 获取所有账户的总和
     * @return the total balance
     */
    public double getTotalBalance(){
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    /**
     * Gets the number of accounts in the bank 获取银行的账户数量
     * @return the number of accounts
     */
    public int size(){
        return accounts.length;
    }


}
