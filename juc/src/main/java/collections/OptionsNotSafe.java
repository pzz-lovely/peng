package collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther lovely
 * @Create 2020-03-25 10:37
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 组合操作并不保证线程安全
 */
public class OptionsNotSafe implements Runnable {
    private static ConcurrentHashMap<String,Integer> scores = new ConcurrentHashMap();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        OptionsNotSafe notSafe = new OptionsNotSafe();
        Thread t1 = new Thread(notSafe);
        Thread t2 = new Thread(notSafe);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                boolean b = scores.replace("小明", score, newScore);
                //scores.put("小明", newScore);
                if (b) {
                    break;
                }
            }
        }
    }
}
