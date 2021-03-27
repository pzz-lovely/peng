package volatiles;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    public static volatile int finished = 0;
    private static void checkFinished(){
        while (finished == 0) {
            //do nothing
            System.out.println("0.0");
        }
        System.out.println("finished");
    }
    private static void finish(){
        finished = 1;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> checkFinished()).start();;
        Thread.sleep(1000);
        finish();
        System.out.println("main finished");

    }

}
