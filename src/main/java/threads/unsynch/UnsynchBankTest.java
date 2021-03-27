package threads.unsynch;

public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;  //初始平衡
    public static final double MAX_AMOUNT = 1000;       //最大金额
    public static final int DELAY = 10;                 //延迟
    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            new Thread(()->{try{
                while (true) {
                    int toAccount = (int) (bank.size() * Math.random());
                    double amount = MAX_AMOUNT *Math.random();
                    bank.transfer(fromAccount, toAccount, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
                //synchronized
            }catch(InterruptedException e){}}).start();
        }
    }
}
