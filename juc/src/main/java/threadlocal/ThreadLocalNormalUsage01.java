package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-03-20 7:50
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 多个线程打印日期
 */
public class ThreadLocalNormalUsage01 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                String date = new ThreadLocalNormalUsage01().date(99 * finalI);
                System.out.println(date);
            }).start();
            Thread.sleep(100);
        }
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从 1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


}
