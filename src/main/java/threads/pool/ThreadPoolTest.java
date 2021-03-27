package threads.pool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory :");
            String directory = in.next();
            System.out.println("Enter keyword:");
            String keyword = in.next();

            ExecutorService pool = Executors.newCachedThreadPool();
            MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
            Future<Integer> future = pool.submit(counter);
            System.out.println(" result size "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * This task counts the files in a directory and its subdirectories that contain a given keyword
     * 此任务计算包含给定关键字的目录及其子目录中的文件
     */
    static class MatchCounter implements Callable<Integer> {
        private File directory;
        private String keyWord;
        private ExecutorService pool;
        private int count;

        /**
         * Constructs a MatchCounter
         * @param directory the directory in which to start the search
         * @param keyWord the keyword to look for
         * @param pool the thread pool for submitting subtasks
         */
        public MatchCounter(File directory, String keyWord, ExecutorService pool) {
            this.directory = directory;
            this.keyWord = keyWord;
            this.pool = pool;
        }

        @Override
        public Integer call() throws Exception {
            count = 0 ;
            try {
                File[] files = directory.listFiles();
                List<Future<Integer>> results = new ArrayList<>();
                for (File file : files) {
                    if (file.isDirectory()) {
                        MatchCounter matchCounter = new MatchCounter(file, keyWord, pool);
                        Future<Integer> result = pool.submit(matchCounter);
                        results.add(result);
                    } else {             //不为目录
                        if (search(file)) count++;
                    }
                }

                for (Future<Integer> future : results) {
                    count += future.get();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return count;
        }

        /**
         * searches a file for a given keyword
         * @param file file the file to search
         * @return true if the keyword is contained in the file
         */
        public boolean search(File file) {
            try (Scanner in = new Scanner(file, "utf-8")) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if(line.contains(keyWord)) found = true;
                }
                return found;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


}
