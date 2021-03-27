package threads.synch2;

import java.util.Arrays;

/**
 * A bank with a number of bank accounts that uses synchronization primitives
 */
public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        while(accounts[from] < amount)
            wait();
        System.out.println(Thread.currentThread().getName());
        accounts[from] -= amount;
        System.out.printf(" %10.f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.println("Total Balance : %10.2f %n ");
    }


    /**
     * Gets the sum of all account balance
     * @return the total balance
     */
    public synchronized double getTotalBalance(){
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    /**
     * Gets the number of accounts in the bank
     * @return the number of accounts 1`2crf
     */
    public int size(){
        return accounts.length;
    }

}
