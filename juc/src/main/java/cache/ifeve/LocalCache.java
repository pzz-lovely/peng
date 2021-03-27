package cache.ifeve;


import com.sun.org.apache.regexp.internal.RE;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import sun.misc.Cleaner;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther lovely
 * @Create 2020-03-26 17:21
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 本地缓存
 * 采用懒过期模式 在查询才判断是否过期
 * 在缓存满了的时候触发自动过期
 */
public class LocalCache<T>{
    private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);
    private static final int DEFAULT_MAX_NUMBER = 100;//默认的最大缓存数

    private final Map<String, Value<T>> cache;  //真正存储数据的Map，使用ConcurrentHashMap
    private final int maxNumber;//最大Value对象数
    //并发控制器，很重要，防止高并发下本地缓存对象个数超过maxNumber
    private final AtomicInteger cur = new AtomicInteger(0);

    private static final Semaphore semaphore = new Semaphore(1);
    private boolean running = true;

    private CleanerTask cleaner;

    public LocalCache(){
        this(DEFAULT_MAX_NUMBER);
    }

    public LocalCache(int maxNumber) {
        this.maxNumber = maxNumber;
        this.cache = new ConcurrentHashMap<>(maxNumber);
        cleaner = new CleanerTask();
    }

    public void start(){
            Timer timer = new Timer();
            timer.schedule(cleaner,2000
                    ,3000);
    }

    public boolean put(String key, T value,long expire) {
        if (key == null || value == null || expire == 0) {
            throw new NullPointerException();
        }
        if (isOver()) {
            flush();
        }
        putValue(key, value, expire);
        return true;
    }


    public void stop(){
        running = false;
        System.exit(1);
    }

    private void putValue(String key, T value,long expire){
        Value<T> v = new Value<T>(System.currentTimeMillis(), expire, value);
        cache.putIfAbsent(key, v);
        cur.incrementAndGet();
    }

    public void flush(){
        cleaner.checkAll();
    }

    public T get(String key) {
        Value<T> tValue = cache.get(key);
        if (tValue == null) {
            return null;
        }
        cur.decrementAndGet();
        return tValue.value;
    }


    public int size(){
        return cur.get();
    }

    /**
     * 是否 到顶
     * @return
     */
    private boolean isOver(){
        return cur.get() > maxNumber;
    }



    /*private void increment(){
        for (; ; ) {
            int c = cur.get();
            boolean flag = cur.compareAndSet(c, ++c);
            if (flag) {
                return;
            }
        }
    }*/

    private void decrement(){
        for (; ; ) {
            int c = cur.get();
            boolean flag = cur.compareAndSet(c, --c);
            if (flag) {
                return;
            }
        }
    }

    private static class Value<T>{
        private long updateTime;//更新时间
        private long expire;    //有效期
        private T value;   //真正的对象

        private Value(long updateTime, long expire, T value) {
            this.updateTime = updateTime;
            this.expire = expire;
            this.value = value;
        }
    }

    private class CleanerTask extends TimerTask{
        @Override
        public void run() {
            while (running) {
                checkAll();
            }
        }

        /**
         * 检查所有的
         */
        public void checkAll(){
            try{
                logger.debug("{} 刷新缓存", Thread.currentThread().getName());
                semaphore.acquire();
                for (Map.Entry<String, Value<T>> entry : cache.entrySet()) {
                    isExpire(entry.getKey(), entry.getValue());
                }
            } catch (InterruptedException e) {
                logger.debug("关闭程序了，清除所有缓存");
                running = false;
            }finally{
                semaphore.release();
            }
        }

        private void isExpire(String key,Value value) {
            logger.debug("开始扫描本地缓存");
            if (overdue(value)) {
                logger.debug("清除缓存{}",key);
                cache.remove(key);
            }
        }

        /**
         * 检查是否过期
         * @param value
         * @return
         */
        private boolean overdue(Value<T> value){
            long current = System.currentTimeMillis();
            return current - value.updateTime > value.expire;
        }
    }

}
