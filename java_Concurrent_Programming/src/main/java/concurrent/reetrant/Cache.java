package concurrent.reetrant;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存实例说明读写锁
 */
public class Cache<K,V> {
    private Map<K, V> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock readLock = rwl.readLock();
    static Lock writeLock = rwl.writeLock();

    //获取一个key1对应的value
    public final Object get(K key) {
        readLock.lock();    //读获取锁
        try{
            return map.get(key);
        }finally{
            readLock.unlock();
        }
    }

    //设置key对应的value，并返回旧的value
    public final Object put(K key,V value) {
        writeLock.lock();
        try{
            return map.put(key, value);
        }finally{
            writeLock.unlock();
        }
    }

    //清空所有的内容
    public final void clear(){
        writeLock.lock();
        try{
            map.clear();
        }finally{
            writeLock.unlock();
        }
    }
}
