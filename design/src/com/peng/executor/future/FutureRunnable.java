package com.peng.executor.future;

/**
 * @Author lovely
 * @Create 2020-09-09 13:51
 * @Description todo
 */
public interface FutureRunnable<T> extends Runnable {
    T get();
}