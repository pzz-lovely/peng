package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Auther lovely
 * @Create 2020-03-09 9:40
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 两个线程交替打印1~100的奇偶数
 */
public class PrintNumber {
    static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            printNumber(1);
        });
        Thread thread2 = new Thread(() -> printNumber(0));
        thread1.start();
        Thread.sleep(10);
        thread2.start();
        //
        /*Thread.sleep(1000);
        object.notifyAll();*/
    }


    private static void printNumber(int number){
        synchronized (object) {
            for (int i = 1; i <= 100; i++) {
                if ((i & 1)== number) {
                    System.out.println(Thread.currentThread().getName()+" "+i);
                    try {
                        object.notify();
                        if(i <= 98 )
                            object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
