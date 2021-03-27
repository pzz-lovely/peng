package threadcoreknowledge.stopthreads;

/**
 * @Auther lovely
 * @Create 2020-03-06 22:21
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 每次 执行循环都会调用sleep或wait方法,
 *那么不需要每次迭代都检查是否已中断 在sleep会响应中断
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            try{
                while (num <= 10000) {
                    if(num % 100 == 0)
                        System.out.println(num + "是一百的倍数");
                    num++;
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000);
        thread.interrupted();
    }
}
