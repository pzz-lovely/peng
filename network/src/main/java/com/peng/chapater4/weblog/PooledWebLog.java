package com.peng.chapater4.weblog;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-04-30 11:45
 * @Description todo
 */
public class PooledWebLog {
    private static final int NUM_THREAD = 4;
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(NUM_THREAD);
    }
}
