package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-26 15:55
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 用装饰着模式，计算器自动添加缓存功能
 */
public class Cache2<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A,V>  computable;

    public Cache2(Computable computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            //synchronized (this) {
                if (result == null) {
                    result =  computable.compute(arg);
                    cache.put(arg, result);
                }
            //}
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        Cache2<String,Integer> cache = new Cache2(new ExpensiveFunction());
        Integer result = cache.compute("13");
        System.out.println("第一次计算结果" + result);
        System.out.println("第二次计算结果" + cache.compute("13"));
    }
}
