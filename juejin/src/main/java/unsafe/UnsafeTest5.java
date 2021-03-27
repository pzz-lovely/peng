package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class Counter{
    //定义了一个volatile的字段count，以便对它的修改所有线程都可见，并且类加载的时候获取count在类中的偏移地址
    private volatile int count = 0;
    private static long offset; //偏移地址
    private static Unsafe unsafe;
    static {
        try{
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            offset = unsafe.objectFieldOffset(Counter.class.getDeclaredField("count"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在increment()方法中，我们通过调用Unsafe的compareAndSwapInt()方法来尝试更新之前获取到的count的值，如果它没有被其它线程更新过，则更新成功，否则不断重试知道成为止
     */
    public void increment(){
        int before = count;
        //失败了就重试知道成功为止
        while (!unsafe.compareAndSwapInt(this, offset, before, before + 1)) {
            before = count;
        }
    }

    public int getCount(){
        return count;
    }

}

public class UnsafeTest5 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService threadPool = Executors.newFixedThreadPool(100); //定义100容量的线程池
        //起100个线程，每个线程自增10000次
        IntStream.range(0, 100).forEach(i -> threadPool.submit(() -> IntStream.range(0, 10000).forEach(j -> counter.increment())));
        threadPool.shutdown();
        Thread.sleep(2000);
        System.out.println(counter.getCount());
    }
}
