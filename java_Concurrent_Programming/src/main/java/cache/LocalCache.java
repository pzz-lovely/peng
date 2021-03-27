package cache;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalCache {
    private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);

    private static final int DEFAULT_MAX_NUMBER = 100;  //Ĭ����󻺴������
    //�����洢���ݵ�map��ʹ��ConcurrentHashMap
    private final Map<String, Value> cache;
    //��������
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
     * ���
     * �ж��Ƿ񳬹�������� �����������һ��ȫ������
     * ���ȫ�����ں󻹲�������false
     * ���� 1 2 ����ԭ�ӵ�������Ҫʹ�õ�����atomicInteger������
     * @param key ��Ӧ��key
     * @param value ֵ
     * @param expire ����ʱ�� ��λ����
     * @return
     */
    public boolean put(String key, Object value, long expire) {
        if (StringUtils.isBlank(key) || value == null || expire < 0) {
            logger.error("���ػ����쳣");
            return false;
        }
        if (!incr()) {  //���CAS����ʧ��ֱ�ӷ������ʧ��
            return false;
        }
        if(isOver()) {   //�ж��Ƿ����
            expireAll();    //����һ��ȫ������
            if (isOver()) { //���μ��
                logger.error("���ػ���putʱȫ�����ں�û�пռ�");
                decr();
                return false;
            }
        }
        putValue(key, value, expire);
        return true;
    }

    /**
     * ��ȡ�жϹ���ʱ��
     * ������ʵ��������
     * @param key
     * @return
     */
    public Object get(String key) {
        Value v = cache.get(key);
        if (v == null) {
            return null;
        }
        if (isExpired(v)) {
            logger.info("���ػ���key={}�Ѿ�����", key);
            removeValue(key);
            return null;
        }
        return v.value;
    }

    private boolean isExpired(Value v) {
        long current = System.currentTimeMillis();
        //��ǰʱ���ȥ����ʱ�� �Ƿ� ���� ����ʱ��
        return current - v.updateTime > v.expire;
    }

    /**
     * ɨ�����еĶ�����Ҫ���ڵĹ���
     */
    private void expireAll(){
        logger.info("��ʼ���ڱ��ػ���");
        for (Map.Entry<String, Value> entry : cache.entrySet()) {
            if (isExpired(entry.getValue())) {
                removeValue(entry.getKey());
            }
        }

    }

    /**
     * Ϊ�˱�֤cur��Map��sizeʱ�̱���һ��
     * @param key
     * @param value
     * @param expire
     */
    private void putValue(String key, Object value, long expire) {
        Value v = new Value(System.currentTimeMillis(), expire, value);
        if (cache.put(key, v) != null) {    //���ڸ���
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
        private long updateTime;//����ʱ��
        private long expire;    //��Ч��
        private Object value;   //�����Ķ���

        private Value(long updateTime, long expire, Object value) {
            this.updateTime = updateTime;
            this.expire = expire;
            this.value = value;
        }
    }
}
