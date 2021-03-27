package mldn.cache;

import java.util.Map;
import java.util.concurrent.*;

public class CacheTest {
    public static void main(String[] args) throws InterruptedException {
        Cache<Long,News> cache = new Cache<>();
        cache.put(1L, new News(1L, "����1"));
        cache.put(2L, new News(2L, "����2"));
        cache.put(3L, new News(3L, "����3"));
        System.out.println(cache.get(1L));
        System.out.println(cache.get(2L));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(cache.get(1L));
    }
}

class Cache<K,V>{
    private static final TimeUnit time = TimeUnit.SECONDS;
    private static final long Delay_Seconds = 2;    //�ӳ�ʱ��2��
    private Map<K, V> cacheObjects = new ConcurrentHashMap<>();
    private BlockingQueue<DelayedItem<Pair>> queue = new DelayQueue<>();
    public Cache(){
        Thread thread = new Thread(()->{
            while (true) {
                try{
                    DelayedItem<Pair> item = Cache.this.queue.take();   //�Ӷ�����ȡ��Ԫ�� û��Ԫ��������
                    if (item != null) {
                        Pair pair = item.getItem();
                        Cache.this.cacheObjects.remove(pair.key, pair.value);//ɾ��Ԫ��
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
        V oldValue = this.cacheObjects.put(key, value); //���Ԫ��
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
         * ��ȡ�ӳ�
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
        return "���ű��Ϊ:"+nid+"����������Ϊ"+this.title;
    }
}
