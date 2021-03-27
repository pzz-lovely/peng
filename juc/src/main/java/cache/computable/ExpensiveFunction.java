package cache.computable;

import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-26 16:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 耗时计算的实现列，实现了Computable接口，但是本身不具备缓存能力，不需要考虑缓存的事情
 */
public class ExpensiveFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return new Integer(arg);
    }
}
