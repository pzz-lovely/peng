package threads.blocking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest2 {
    private static final int FILE_QUEUE_SIZE = 10;  //文件队列大小
    private static final int  SEARCH_THREADS = 100; //线程数
    private static final File DUMMY = new File("");
    //构造一个带有指定容量的 数组阻塞队列
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter base directory 输入基目录 :");
            String directory = input.next();
            System.out.println("Enter file in keyword 输入文件里面的关键词 :");
            String keyword = input.next();
            new Thread(()->{
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);           //添加空文件
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            for (int i = 1; i <= SEARCH_THREADS; i++) {
                new Thread(()->{
                    boolean done = false;
                    while (!done) {
                        try {
                            File file = queue.take();       //检索并删除队列的头
                            if(file==DUMMY){                //判断是否为文件
                                queue.put(file);
                                done = true;
                            }else search(file,keyword);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                System.out.println(queue);
            }

        }
    }

    /**
     * Recursively enumerate all files int a given directory and its subdirectories
     * 递归给定目录及其子目录中的所有文件
     * @param directory 文件
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();       //获取文件数组
        for (File file : files) {
            if(file.isDirectory())enumerate(file);  //如果是目录则递归调用
            else queue.put(file);                   //如果不是 则将文件添加到 阻塞队列
        }
    }

    public static void search(File file, String keyword) {
        try (Scanner in = new Scanner(file, "utf-8")) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.next();
                if (line.contains(keyword))
                    System.out.printf("路径{%s} : 找到的次数{%d} : 找到的行{%s} %n",file.getPath(),lineNumber,line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
