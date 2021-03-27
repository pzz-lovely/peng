package threads.blocking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;      //文件队列大小
    private static final int SEARCH_THREADS = 100;      //搜索线索
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory:");
            String directory = in.next();
            System.out.println("Enter keyword ():");
            String keyword = in.next();
            Runnable enumerator = ()->{
                try{
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(enumerator).start();
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = ()->{
                    try{
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();       //移除最后一个元素
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            }else search(file,keyword);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    long nowL = System.currentTimeMillis();
                    System.out.println("多少毫秒"+(l-nowL));
                };
                new Thread(searcher).start();
            }
        }
    }

    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if(file.isDirectory()) enumerate(file); //判断是否是目录 是就递归调用
            else queue.put(file);                   //不是 就在队列中添加
        }
        print();
    }

    public static void print(){
        for (File file : queue) {
            System.out.println(file.getAbsolutePath());
        }
    }

    public static void search(File file, String keyword) throws FileNotFoundException {
        try (Scanner in = new Scanner(file, "utf-8")) {
            int lineNumber = 0;
            while (in.hasNext()) {
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("路径:%s 查找的次数:%d:%s%n",file.getPath(),lineNumber,line);
            }
        }
    }
}
