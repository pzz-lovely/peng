package com.peng.chapater3.zip;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-04-30 9:15
 * @Description todo
 */
public class GZipAllFiles {
    private static final int THREAD_COUNT = 4;
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
        File file = new File("E:\\work\\Data");
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if(!files[i].isDirectory()) { //不递归处理目录
                        Runnable task = new GZipRunnable(files[i]);
                        service.submit(task);
                    }
                }
            }else{
                Runnable task = new GZipRunnable(file);
                service.submit(task);
            }
        }
        service.shutdown();
    }
}
