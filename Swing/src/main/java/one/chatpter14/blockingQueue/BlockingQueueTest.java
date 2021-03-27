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
    private static final int FILE_QUEUE_SIZE = 10;  //�ļ����еĴ�С
    private static final int SEARCH_THREAD = 100;   //�����߳���
    private static final File DUMMY = new File(""); //����Ʒ
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

            new Thread(enumerator).start(); //�ȶ�ȡ��Ŀ¼ Ȼ����ӵ� ��������

            for (int i = 0; i < SEARCH_THREAD; i++) {
                Runnable searcher = ()->{
                    try{
                        boolean done = false;
                        while (!done) {
                            File file =queue.take();//ȡ��ͷ��Ԫ�ز�ɾ�� �������� ������
                            if (file == DUMMY) {
                                queue.put(file);    //������ӵ�ͷ��
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
        File[] files = directory.listFiles();   //��ȡ��ǰ��Ŀ¼�µ��ļ�
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
        //�Զ��ر���Դ
        try (Scanner in = new Scanner(file, "UTF-8")) {
            int lineNumber = 0;
            while (in.hasNext()) {              //�ж���һ���Ƿ�����Դ
                lineNumber++;
                String line = in.nextLine();    //��ȡһ�е�����
                if(line.contains(keyWord))      //���ҵ�ǰ�����й� keyword
                    System.out.printf("name:%s path=%s Lines=%d data%s%n",Thread.currentThread(),file.getPath(),lineNumber,
                            line);
            }
        }
    }


}
