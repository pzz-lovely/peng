package cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-26 15:55
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 最简单的缓存形式
 */
public class Cache1 {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        if (result == null) {
            //如果缓存中找不到，那么需要现在计算一下结果，并且保存到HashMap中
            synchronized (this) {
                if(result == null)
                    result = dpComputer(userId);
            }
            cache.put(userId, result);
        }
        return result;
    }

    public void clear(){
        cache.clear();
    }
    
    
    
    private Integer dpComputer(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        Cache1 cache1 = new Cache1();
        Integer result = cache1.computer("13");
        System.out.println("第一次计算结果" + result);
        System.out.println("第二次计算结果" + result);
    }
}
