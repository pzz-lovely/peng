package cache;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalCache {
    private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);

    private static final int DEFAULT_MAX_NUMBER = 100;  //默认最大缓存对象数
    //真正存储数据的map，使用ConcurrentHashMap
    private final Map<String, Value> cache;
    //最大对象数
    private final int maxNumber;
    private final AtomicInteger cur = new AtomicInteger(0);

    public LocalCache(){
        this(DEFAULT_MAX_NUMBER);
    }

    public Map<String, Value> getCache() {
        return cache;
    }

    public AtomicInteger getCur() {
        return cur;
    }

    public LocalCache(int maxNumber) {
        this.maxNumber = maxNumber;
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * 添加
     * 判断是否超过最大限制 如果超过触发一次全量过期
     * 如果全量过期后还不过返回false
     * 由于 1 2 不是原子的所以需要使用单独的atomicInteger来控制
     * @param key 对应的key
     * @param value 值
     * @param expire 过期时间 单位毫秒
     * @return
     */
    public boolean put(String key, Object value, long expire) {
        if (StringUtils.isBlank(key) || value == null || expire < 0) {
            logger.error("本地缓存异常");
            return false;
        }
        if (!incr()) {  //如果CAS增加失败直接返回添加失败
            return false;
        }
        if(isOver()) {   //判断是否过期
            expireAll();    //触发一次全量过期
            if (isOver()) { //二次检查
                logger.error("本地缓存put时全量过期后还没有空间");
                decr();
                return false;
            }
        }
        putValue(key, value, expire);
        return true;
    }

    /**
     * 获取判断过期时间
     * 在这里实现懒过期
     * @param key
     * @return
     */
    public Object get(String key) {
        Value v = cache.get(key);
        if (v == null) {
            return null;
        }
        if (isExpired(v)) {
            logger.info("本地缓存key={}已经过期", key);
            removeValue(key);
            return null;
        }
        return v.value;
    }

    private boolean isExpired(Value v) {
        long current = System.currentTimeMillis();
        //当前时间减去过期时间 是否 大于 过期时间
        return current - v.updateTime > v.expire;
    }

    /**
     * 扫描所有的独享需要过期的过期
     */
    private void expireAll(){
        logger.info("开始过期本地缓存");
        for (Map.Entry<String, Value> entry : cache.entrySet()) {
            if (isExpired(entry.getValue())) {
                removeValue(entry.getKey());
            }
        }

    }

    /**
     * 为了保证cur和Map的size时刻保持一致
     * @param key
     * @param value
     * @param expire
     */
    private void putValue(String key, Object value, long expire) {
        Value v = new Value(System.currentTimeMillis(), expire, value);
        if (cache.put(key, v) != null) {    //存在覆盖
            decr();
        }
    }

    private void removeValue(String key) {
        if (cache.remove(key) != null) {
            decr();
        }
    }

    private boolean isOver(){
        return cur.get() > maxNumber;
    }

    private boolean incr(){
        int c = cur.get();
        return cur.compareAndSet(c, ++c);
    }

    private void decr(){
        for (; ; ) {
            int c = cur.get();
            if (c == 0) {
                logger.error("LocalCache decr cur is 0");
                return ;
            }
            if (cur.compareAndSet(c, --c)) {
                return;
            }
        }
    }


    private static class Value {
        private long updateTime;//更新时间
        private long expire;    //有效期
        private Object value;   //真正的对象

        private Value(long updateTime, long expire, Object value) {
            this.updateTime = updateTime;
            this.expire = expire;
            this.value = value;
        }
    }
}
