package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;
import cache.computable.MayFail;

import java.util.concurrent.*;

/**
 * @Auther lovely
 * @Create 2020-03-26 15:55
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 利用future避免重复计算
 */
public class Cache3<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, FutureTask<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A,V>  computable;

    public Cache3(Computable computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws Exception {
        Future<V> result = cache.get(arg);
        if (result == null) {
            Callable<V> callable = ()->{
                return computable.compute(arg);};
            FutureTask<V> ft = new FutureTask<>(callable);
            result = cache.putIfAbsent(arg, ft);
            if (result == null) {
                System.out.println("从FutureTask调用了计算函数");
                result = ft;//替换引用，不然都无法 将Callable类传到FutureTask里面
                ft.run();   //有当前进入的之歌线程 执行call方法
            }
        }
        return result.get();
    }

    public static void main(String[] args) throws Exception {
        Cache3<String,Integer> cache = new Cache3(new MayFail());
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int no = i / 2;
            service.submit(()->{
                Integer result = null;
                try {
                    result = cache.compute(no+"6");
                    System.out.println(no+" 第一次计算结果" + result);
                    System.out.println(no+" 第二次计算结果" + cache.compute(no+"6"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        /*service.shutdownNow();*/
    }
}
