package block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ��һ��Ŀ¼�Լ�������Ŀ¼�����������ļ�����ӡ����ָ���ؼ��ֵ���
 */
public class BlockingTest {
    private static final int File_Queue_Size = 10;
    private static final int Search_Threads = 1000;     //�������߳���
    private static final File Dummy = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(File_Queue_Size);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory Ҫ������Ŀ¼ :");
            String directory = in.nextLine();
            System.out.println("Enter keyword Ҫ���ҵ����� :");
            String keyWord = in.next();
            //����߳���Ҫ�� �����������Ԫ��
            Runnable enumerator = ()->{
                try{
                    enumerate(new File(directory));
                    queue.put(Dummy);   //�������� ������
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(enumerator).start();
            //�����ļ��е�����
            for (int i = 0; i < Search_Threads; i++) {
                Runnable searcher = ()->{
                    try{
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();   //�Ƴ�������ͷԪ��,�������Ϊ��������
                            if(file == Dummy) {  //�Ƿ�Ϊ���ļ�
                                queue.put(file);        //���Ԫ�� �����������������
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
     * ��ȡ�ļ� �µ� ����Ŀ¼���ļ�  �ļ��ͱ��浽��������
     * @param directory ָ��Ŀ¼
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if(file.isDirectory()) enumerate(file) ;        //�ݹ���� �ж��ǲ���Ŀ¼
            else queue.put(file);           //���� ������ļ�������������
        }
    }

    /**
     * ��ָ�� �ļ� ����ָ�� ����
     * @param file ָ�� �ļ�
     * @param keyWord ָ������
     */
    public static void search(File file, String keyWord) {
        try (Scanner in = new Scanner(file, "utf-8")) { //ɨ���ļ�
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;           //ɨ�赽�Ĵ�������
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
