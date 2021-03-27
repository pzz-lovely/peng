package locks.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-21 10:56
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示多线程预订电影院座位
 */
public class CinemaBookSeat {

    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "开始预订座位");
            Thread.sleep(1000);
            System.out.println("预定了座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
    }
}
