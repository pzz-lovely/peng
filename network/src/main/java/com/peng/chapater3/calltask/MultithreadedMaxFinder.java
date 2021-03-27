package com.peng.chapater3.calltask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther lovely
 * @Create 2020-04-30 8:51
 * @Description 找寻一个数组最大的数字
 */
public class MultithreadedMaxFinder {
    public static int max(int data[]) throws ExecutionException, InterruptedException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalArgumentException();
        }
        //将任务分解分成两部分
        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

        //创建两个线程
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        return Math.max(future1.get(), future2.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int data[] = {21, 23, 2131, 24, 2, 34, 12, 2, 45, 7};
        System.out.println(max(data));
    }
}
