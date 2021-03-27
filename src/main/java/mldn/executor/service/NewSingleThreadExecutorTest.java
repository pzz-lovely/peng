package mldn.executor.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 设置单线程池
 */
public class NewSingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }
}
