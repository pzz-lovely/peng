package one.chatpter14.blockingQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Auther lovely
 * @Create 2020-02-26 15:07
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;  //文件队列的大小
    private static final int SEARCH_THREAD = 100;   //搜索线程数
    private static final File DUMMY = new File(""); //仿制品
    private static BlockingQueue<File> queue = new ArrayBlockingQueue(FILE_QUEUE_SIZE);


    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory");
            String directory = in.nextLine();
            System.out.println("Enter keyword");
            String keyword = in.nextLine();
            Runnable enumerator = () -> {
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            new Thread(enumerator).start(); //先读取到目录 然后添加到 阻塞队列

            for (int i = 0; i < SEARCH_THREAD; i++) {
                Runnable searcher = ()->{
                    try{
                        boolean done = false;
                        while (!done) {
                            File file =queue.take();//取出头部元素并删除 队列满了 则阻塞
                            if (file == DUMMY) {
                                queue.put(file);    //重新添加到头部
                                done = true;
                            }
                            else
                                search(file,keyword);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher,"searcher name "+i).start();
            }
        }
    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories
     * @param directory directory the directory in which to start
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();   //获取当前，目录下的文件
        for (File file : files) {
            if(file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }

    /**
     * Searches a file a given keyword and prints all matching lines
     * @param file the file to search
     * @param keyWord the keyword to search for
     * @throws FileNotFoundException
     */
    public static void search(File file, String keyWord) throws FileNotFoundException {
        //自动关闭资源
        try (Scanner in = new Scanner(file, "UTF-8")) {
            int lineNumber = 0;
            while (in.hasNext()) {              //判断下一行是否有资源
                lineNumber++;
                String line = in.nextLine();    //获取一行的数据
                if(line.contains(keyWord))      //查找当前数据有关 keyword
                    System.out.printf("name:%s path=%s Lines=%d data%s%n",Thread.currentThread(),file.getPath(),lineNumber,
                            line);
            }
        }
    }


}
