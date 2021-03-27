package courseware;

/**
 * @Auther lovely
 * @Create 2020-05-15 20:10
 * @Description 展示New,Runnable,Blocking,Waiting,Terminated
 */
public class ThreadStateTest {
    private static Object obj = new Object();   //作为锁
    public static void main(String[] args) {
        // 新建状态 New
        Thread thread1 = new Thread(()->{task();});
        Thread thread2 = new Thread(()->{task();});
        //可运行状态 runnable
        thread1.start();
        thread2.start();
    }
    /*具体的方法执行*/
    public static void task() {
        synchronized (obj) {
            try {
                obj.wait();
                //obj.wait(3000);
                System.out.println(Thread.currentThread().getName()+"正在执行");
                System.out.println(Thread.currentThread().getName()+"执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

