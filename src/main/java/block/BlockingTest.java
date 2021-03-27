package block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 在一个目录以及所有子目录下搜索所有文件，打印包含指定关键字的行
 */
public class BlockingTest {
    private static final int File_Queue_Size = 10;
    private static final int Search_Threads = 1000;     //搜索的线程数
    private static final File Dummy = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(File_Queue_Size);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory 要搜索的目录 :");
            String directory = in.nextLine();
            System.out.println("Enter keyword 要查找的内容 :");
            String keyWord = in.next();
            //这个线程主要是 往队列中添加元素
            Runnable enumerator = ()->{
                try{
                    enumerate(new File(directory));
                    queue.put(Dummy);   //队列已满 则阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(enumerator).start();
            //搜索文件中的内容
            for (int i = 0; i < Search_Threads; i++) {
                Runnable searcher = ()->{
                    try{
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();   //移出并返回头元素,如果队列为空则阻塞
                            if(file == Dummy) {  //是否为空文件
                                queue.put(file);        //添加元素 如果队里满了则阻塞
                                done = true;
                            }else
                                search(file, keyWord);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    /**
     * 读取文件 下的 所有目录和文件  文件就保存到阻塞队列
     * @param directory 指定目录
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if(file.isDirectory()) enumerate(file) ;        //递归调用 判断是不是目录
            else queue.put(file);           //不是 就添加文件到阻塞队列中
        }
    }

    /**
     * 从指定 文件 查找指定 内容
     * @param file 指定 文件
     * @param keyWord 指定内容
     */
    public static void search(File file, String keyWord) {
        try (Scanner in = new Scanner(file, "utf-8")) { //扫描文件
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;           //扫描到的次数增加
                String line = in.nextLine();    //
                if (line.contains(keyWord)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
