package com.peng.executor;

import java.util.concurrent.BlockingQueue;

/**
 * @Author lovely
 * @Create 2020-09-09 12:55
 * @Description todo
 */
public interface RejectPolicy {
    void reject(Runnable command, MyThreadExecutorService myThreadExecutorService);
}