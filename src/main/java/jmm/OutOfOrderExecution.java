package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther lovely
 * @Create 2020-03-14 17:03
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 重排序
 * 直到某个条件才停止,测试小概率事件
 */
public class OutOfOrderExecution {
    private static int x =0,y = 0,a=0,b=0;
    public static void main(String[] args) throws InterruptedException {
        int i =0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch count = new CountDownLatch(1);
            Thread thread1 = new Thread(() -> {
                try {
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;  //这两个并没有依赖性 可能会重排序
                x = b;
            });
            Thread thread2 = new Thread(() -> {
                try {
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            thread1.start();
            thread2.start();
            count.countDown();
            thread1.join();
            thread2.join();
            System.out.println("x = " + x + ", y = " + y);
            if(x == 1/*0*/ && y == 1/*0*/) {
                break;
            }
        }
        System.out.println("第 i = " + i);
    }
}
