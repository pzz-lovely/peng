package pool.yes_value;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface FutureExecutor<T> {
    <T> Future<T> submit(Callable<T> callable);
}
