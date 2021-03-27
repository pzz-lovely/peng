package future;


import java.util.concurrent.*;

/**
 * @Auther lovely
 * @Create 2020-03-26 15:34
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task();
        FutureTask futureTask = new FutureTask(task);
        /*new Thread(futureTask).start();*/
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(futureTask);
        System.out.println(futureTask.get());
    }

    static class Task implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            return 1 *3;
        }
    }
}
