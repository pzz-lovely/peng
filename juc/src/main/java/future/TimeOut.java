package future;

import java.util.concurrent.*;

/**
 * @Auther lovely
 * @Create 2020-03-26 14:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TimeOut {
    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");
    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    static class Ad{
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("旅游订票哪家强？找某程");
        }
    }

    private static void printAd(){
        Future<Ad> future = service.submit(new FetchAdTask());
        Ad ad  = null;
        try{
            ad = future.get(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            ad = new Ad("超时了 ，时候的默认广告");
            System.out.println("超时了，未获取到广告");
            /**false
             *
             * true
             * 含义：如果这个任务还在进行中，就把这个任务中断
             */
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        printAd();
    }
}
