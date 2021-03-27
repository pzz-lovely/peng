package chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Profiler {
    //��һ��get()��������ʱ���ʼ��(���set����û�õ���)��ÿ���̻߳����һ��
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin (){
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Cost : " + Profiler.end());
    }
}
