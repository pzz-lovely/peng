package threads.future;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory 要查找的目录(/usr/local/jdk) : ");
            String directory = in.nextLine();
            System.out.println("enter keyword (volatile) :");
            String keyWord = in.nextLine();


            MatchCounter counter = new MatchCounter(new File(directory), keyWord);
            FutureTask<Integer> task = new FutureTask<>(counter);
            new Thread(task).start();
            System.out.println(task.get()+" matching files");
        }
    }

    static class MatchCounter implements Callable<Integer> {
        private File directory;
        private String keyWord;
        public MatchCounter(File directory, String keyWord) {
            this.directory = directory;
            this.keyWord = keyWord;
        }

        @Override
        public Integer call() throws Exception {
            int count = 0 ;         //总和
            try {
                File[] files = directory.listFiles();   //获取当前目录下的所有文件
                List<Future<Integer>> results = new ArrayList<>();
                for (File file : files) {
                    if (file.isDirectory()) {
                        MatchCounter counter = new MatchCounter(file, keyWord);
                        FutureTask<Integer> task = new FutureTask<>(counter);
                        results.add(task);
                        new Thread(task).start();       //启动线程
                    }else {
                        if(search(file)) count++;
                    }
                }
                for (Future<Integer> result : results) {        //遍历 Future集合
                    try {
                        count += result.get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

            }
            return count;
        }

        /**
         * Searches a file for a given keyword
         * @param file file the file to search
         * @return true if the keyword is contained in the file
         */
        public boolean search(File file) {
            try (Scanner in = new Scanner(System.in)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyWord)) found = true;
                }
                return found;
            }
        }
    }

}
