package one.chatpter14.threadPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @Auther lovely
 * @Create 2020-02-26 20:21
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */

/**
 * This tack counts the files in a directory and its subdirectories that contain a given keyword
 */
public class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyWord;
    private ExecutorService pool;
    private int count;


    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory ");

        }
    }

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
                    MatchCounter counter = new MatchCounter(file, keyWord, pool);
                    Future<Integer> result = pool.submit(counter);
                    results.add(result);
                } else {
                    if (search(file)) count++;
                }
            }
            for (Future<Integer> result : results) {
                count += result.get();
            }
        } catch (InterruptedException e) {

        }
        return count;
    }

    public boolean search(File file) {
            try (Scanner in = new Scanner(file, "utf-8")) {
                boolean found = false;
                while (!found && in.hasNext()) {
                    String line = in.nextLine();
                    if(line.contains(keyWord)) found = true;
                }
                return found;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return false;
    }
}
