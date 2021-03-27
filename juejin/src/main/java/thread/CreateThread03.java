package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ïß³Ì³Ø
 */
public class CreateThread03 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Thread(new service(),"Thread - "+i));
        }
    }
}

class service implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
    }
}
