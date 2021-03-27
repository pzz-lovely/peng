package mldn.cache;

import java.util.Map;
import java.util.concurrent.*;

public class CacheTest {
    public static void main(String[] args) throws InterruptedException {
        Cache<Long,News> cache = new Cache<>();
        cache.put(1L, new News(1L, "新闻1"));
        cache.put(2L, new News(2L, "新闻2"));
        cache.put(3L, new News(3L, "新闻3"));
        System.out.println(cache.get(1L));
        System.out.println(cache.get(2L));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(cache.get(1L));
    }
}

class Cache<K,V>{
    private static final TimeUnit time = TimeUnit.SECONDS;
    private static final long Delay_Seconds = 2;    //延迟时间2秒
    private Map<K, V> cacheObjects = new ConcurrentHashMap<>();
    private BlockingQueue<DelayedItem<Pair>> queue = new DelayQueue<>();
    public Cache(){
        Thread thread = new Thread(()->{
            while (true) {
                try{
                    DelayedItem<Pair> item = Cache.this.queue.take();   //从队列中取出元素 没有元素则阻塞
                    if (item != null) {
                        Pair pair = item.getItem();
                        Cache.this.cacheObjects.remove(pair.key, pair.value);//删除元素
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void put(K key, V value) throws InterruptedException {
        V oldValue = this.cacheObjects.put(key, value); //添加元素
        if (oldValue != null) {
            this.queue.remove(oldValue);
        }
        this.queue.put(new DelayedItem<>(new Pair(key, value), Delay_Seconds, time));
    }

    public V get(K key) {
        return this.cacheObjects.get(key);
    }


    private class Pair{
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    private class DelayedItem<T> implements Delayed{
        private T item;
        private long delay;
        private long expire;

        public DelayedItem(T item, long delay, TimeUnit unit) {
            this.item = item;
            this.delay = TimeUnit.MILLISECONDS.convert(delay,unit);
            this.expire =  System.currentTimeMillis()+ this.delay;
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.delay - this.getDelay(TimeUnit.MILLISECONDS));
        }

        /**
         * 获取延迟
         * @param unit
         * @return
         */
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public T getItem(){
            return this.item;
        }
    }
}

class News{
    private long nid;
    private String title;

    News(long nid, String title) {
        this.nid = nid;
        this.title = title;
    }

    @Override
    public String toString() {
        return "新闻编号为:"+nid+"，新闻主题为"+this.title;
    }
}
