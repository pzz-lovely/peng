package threadcoreknowledge.stopthreads;

/**
 * @Auther lovely
 * @Create 2020-03-06 22:07
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 带有 sleep中断线程的写法
 *
 * 如果线程在睡眠过程中受到了中断 则抛出异常
 */
public class RightWayStopThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            try{
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if(num % 100 == 0)
                        System.out.println(num + "是一百的倍数");
                    num++;
                }
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupted();
    }
}
