package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-20 7:50
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 一千个线程池打印日期 SimpleDateFormat为成员变量 ThreadLocal解决方案 保证了内存 高效利用内存
 */
public class ThreadLocalNormalUsage05 {
    private static ExecutorService service = Executors.newFixedThreadPool(10);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            service.submit(
            ()->{
                String date = new ThreadLocalNormalUsage05().date(finalI);
                System.out.println(date);
            });
        }
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从 1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        String format = null;
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal1.get();
        format = dateFormat.format(date);
        return format;
    }


}

class ThreadSafeFormatter {


    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal1 = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
