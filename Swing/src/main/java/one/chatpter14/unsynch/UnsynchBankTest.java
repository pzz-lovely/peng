package one.chatpter14.unsynch;

/**
 * @Auther lovely
 * @Create 2020-02-25 17:03
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class UnsynchBankTest {
    public static final int N_ACCOUNTS = 100;           //人数
    public static final double INITIAL_BALANCE = 1000; //初始金额
    public static final double MAX_AMOUNT = 3;      //线程数
    public static final int DELAY = 1000;

    public static void main(String[] args) {
        final Bank bank = new Bank(N_ACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < MAX_AMOUNT; i++) {
            int fromAccount = i;
            new Thread(
                    () -> {
                        try {
                            while (true) {
                                int toAccount = (int) (bank.size() * Math.random());
                                double amount = MAX_AMOUNT * Math.random();
                                bank.transfer(fromAccount, toAccount, 2000);
                                Thread.sleep((int) (DELAY * Math.random()));
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }, "Thread account " + i).start();
        }

    }

}
