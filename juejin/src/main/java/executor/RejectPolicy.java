package executor;

/**
 * @Auther lovely
 * @Create 2020-08-18 17:30
 * @Description todo
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
