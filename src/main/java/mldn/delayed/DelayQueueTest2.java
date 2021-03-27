package mldn.delayed;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 实现缓存操作
 */
public class DelayQueueTest2 {
    public static void main(String[] args) {

    }
}
class Cache<K,V>{
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private ConcurrentHashMap<K, V> objects = new ConcurrentHashMap<K, V>();
    private BlockingQueue<DelayedItem<Pair>> queue = new DelayQueue<>();
    public Cache(){
        Thread thread = new Thread(()->{
            while (true) {
                try{
                    DelayedItem<Pair> item = Cache.this.queue.take();
                    if (item != null) {
                        Pair pair = item.getItem();
                        Cache.this.objects.remove(pair.key, pair.value);
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
        V oldValue = objects.put(key, value);
        if (oldValue != null) {
            this.queue.remove(oldValue);
        }
        this.queue.put(new DelayedItem<Pair>(new Pair(key, value), 2, UNIT));
    }

    public V get(K key) {
        return this.objects.get(key);
    }

    private class DelayedItem<T> implements Delayed{
        private T item;             //数据项
        private long delay ;        //保存时间
        private long expire;        //失效时间
        private DelayedItem(T item,long delay,TimeUnit unit){
            this.item = item;
            this.delay = TimeUnit.MILLISECONDS.convert(delay,UNIT);//转换为毫秒数
            this.expire = System.currentTimeMillis()+this.delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire -System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this.delay-this.getDelay(TimeUnit.MILLISECONDS));
        }
        public T getItem(){
            return this.item;
        }
    }

    private class Pair{
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

class News{
    private long id;
    private String title;

    public News(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "新闻标题:"+id+" 标题"+title;
    }
}