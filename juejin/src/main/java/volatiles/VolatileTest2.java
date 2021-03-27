package volatiles;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

class Config{
    String name;
}
public class VolatileTest2 {
    private static Config config = null;
    private static volatile boolean initialized = false;

    public static void main(String[] args) {
        new Thread(()->{
            config = new Config();
            config.name = "config";
            initialized = true;
        }).start();

        new Thread(()->{
            while (!initialized) {
                LockSupport.parkNanos(TimeUnit.MICROSECONDS.toNanos(100));
            }
            String name = config.name;
        }).start();
    }
}
