package pool.not_value;

import pool.not_value.imp.MyThreadPoolExecutor;

/**
 * �ܽӲ��Խӿ�
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
