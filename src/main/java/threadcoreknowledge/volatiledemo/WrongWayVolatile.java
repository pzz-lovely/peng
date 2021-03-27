package threadcoreknowledge.volatiledemo;

/**
 * @Auther lovely
 * @Create 2020-03-07 10:11
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description volatile的局限;看似可行
 */
public class WrongWayVolatile implements Runnable {
    private static volatile boolean canceled = false;

    @Override
    public void run() {
        try {
            int num = 0;
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0)
                    System.out.println(num + "是100的倍数");
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new WrongWayVolatile();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread.sleep(5000);
        canceled = true;
    }


}
