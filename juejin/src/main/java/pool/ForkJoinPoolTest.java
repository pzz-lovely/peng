package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther lovely
 * @Create 2020-08-24 11:41
 * @Description ����1�ڸ������ĺ�
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // ��������
        int length = 10000000;
        long[] arr = new long[length];

        for (int i = 0; i < length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        }

        // ���߳�
        singleThreadSum(arr);

        // ThreadPoolExecutor�̳߳�
        multiThreadSum(arr);

        // ForkJoinPool�̳߳�
        forkJoinSum(arr);
    }



    private static void singleThreadSum(long[] arr){
        long start = System.currentTimeMillis();

        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            // ģ���ʱ
            sum += (arr[i] / 3 * 3 / 3 * 3 / 3 * 3 / 3 * 3 / 3 * 3);
        }

        System.out.println("sum��" + sum);
        System.out.println("single thread elapse:" + (System.currentTimeMillis() - start));
    }


    private static void multiThreadSum(long[] arr) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        int count = 8;
        ExecutorService threadPool = Executors.newFixedThreadPool(count);

        List<Future<Long>> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int num = i;
            // �ֶ��ύ����
            Future<Long> future = threadPool.submit(()->{
                long sum = 0;
                for (int j = arr.length / count * num; j < (arr
                        .length / count * (num + 1)); j++) {
                    sum += (arr[j]/3*3/3*3/3*3/3*3/3*3);
                }
                return sum;
            });
            list.add(future);
        }


        // ÿ���ν�����
        long sum = 0;
        for (Future<Long> future : list) {
            sum += future.get();
        }

        System.out.println("sum: " + sum);
        System.out.println("multi thread elapse:" + (System.currentTimeMillis() - start));
    }


    private static void forkJoinSum(long[] arr) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        // �ύ����
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(new SumTask(arr, 0, arr.length));
        // ��ȡ���
        Long sum = forkJoinTask.get();

        forkJoinPool.shutdown();

        System.out.println("sum: " + sum);
        System.out.println("fork join elapse: " + (System.currentTimeMillis() - start));
    }

    private static class SumTask extends RecursiveTask<Long> {
        private long[] arr;
        private int from;
        private int to;

        public SumTask(long[] arr, int from, int to) {
            this.arr = arr;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // С��1000��ʱ��ֱ����ӣ���������
            if (to - from <= 1000) {
                long sum = 0;
                for (int i = from; i < to; i++) {
                    // ģ���ʱ
                    sum += (arr[i]/3*3/3*3/3*3/3*3/3*3);
                }
                return sum;
            }

            // �ֳ���������
            int middle = (from + to) / 2;
            SumTask left = new SumTask(arr, from, middle);
            SumTask right = new SumTask(arr, middle, to);

            // �ύ��ߵ�����
            left.fork();
            // �ұߵ�����ֱ�����õ�ǰ�̼߳��㣬��Լ����
            Long rightResult = right.compute();
            // �ȴ���߼������
            Long leftResult = left.join();
            // ���ؽ��
            return leftResult + rightResult;
        }
    }
}
