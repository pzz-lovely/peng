package mldn.executor.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  创建固定线程
 */
public class NewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
    }
}
