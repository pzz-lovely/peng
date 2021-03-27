package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-20 7:50
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 一千个线程池打印日期 SimpleDateFormat为成员变量 加锁解决
 */
public class ThreadLocalNormalUsage04 {
    private static ExecutorService service = Executors.newFixedThreadPool(10);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            service.submit(
            ()->{
                String date = new ThreadLocalNormalUsage04().date(finalI);
                System.out.println(date);
            });
            Thread.sleep(100);
        }
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从 1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        String format = null;
        synchronized (this) {
            format = simpleDateFormat.format(date);
        }
        return format;
    }


}
