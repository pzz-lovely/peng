package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Auther lovely
 * @Create 2020-03-22 18:03
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {
    static Candidate tom = new Candidate();
    static Candidate peter = new Candidate();

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdate =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");



    public static class Candidate{
         volatile int score = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdate.getAndIncrement(tom);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AtomicIntegerFieldUpdaterDemo());
        Thread thread2 = new Thread(new AtomicIntegerFieldUpdaterDemo());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("普通变量" + peter.score);
        System.out.println("升级后的结果"+tom.score);
    }
}
