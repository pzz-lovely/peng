package one.chatpter14.future;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Auther lovely
 * @Create 2020-02-26 17:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory");
            String directory = in.nextLine();
            System.out.print("Enter keyword ");
            String keyword = in.nextLine();

            MatchCounter counter = new MatchCounter(new File(directory), keyword);
            FutureTask<Integer> future = new FutureTask<>(counter);
            Thread t = new Thread(future);
            t.start();
            try{
                System.out.println(future.get()+" matching files");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * this task counts the files in a directory and its subdirectories that contain a given keyword
 */
class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyWord;

    public MatchCounter(File directory, String keyWord) {
        this.directory = directory;
        this.keyWord = keyWord;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    MatchCounter counter = new MatchCounter(file, keyWord);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                } else {
                    if (search(file)) count++;
                }
            }
            for (Future<Integer> future : results) {
                try {
                    count += future.get();
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Searches a file for a given keyword
     * @param file the file to search
     * @return if the keyword is contained in the file
     */
    private boolean search(File file) {
            try (Scanner in = new Scanner(file, "utf-8")) {
                boolean found = false;
                while (!found && in.hasNext()) {
                    String line = in.nextLine();
                    if(line.contains(keyWord)) found = true;
                }
                return found;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

    }
}
