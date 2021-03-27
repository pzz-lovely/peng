package pool.not_value;

import pool.not_value.imp.MyThreadPoolExecutor;

/**
 * 拒接策略接口
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
