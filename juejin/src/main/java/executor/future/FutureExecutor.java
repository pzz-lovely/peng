package executor.future;

import java.util.concurrent.Callable;

/**
 * @Auther lovely
 * @Create 2020-08-19 16:13
 * @Description todo
 */
public interface FutureExecutor<T> {

    Future<T> submit(Callable<T> task);
}
