package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-03-20 7:50
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 两个线程打印日期
 */
public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        new Thread(()->{
            String date = new ThreadLocalNormalUsage00().date(10);
            System.out.println(date);
        }).start();
        new Thread(()->{
            String date = new ThreadLocalNormalUsage00().date(104707);
            System.out.println(date);
        }).start();
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从 1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


}
