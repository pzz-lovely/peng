package juc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * ����1�ڸ������ĺ�
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int length = 100000000;
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        }
        //���߳�
        //singleThreadSum(arr);
        //ThreadServicePool�̳߳�
        //multiThreadSum(arr);
        //ForkJointTask�̳߳�
        forkJointSum(arr);

    }

    private static void singleThreadSum(long[] arr) {
        long start = System.currentTimeMillis();
        long sum = 0 ;
        for (int i = 0; i < arr.length; i++) {
            sum += (arr[i] /3*3/3*3/3*3/3*3/3*3);
        }
        System.out.println("sum = " + sum);
        System.out.println("single Thread elapse:" + (System.currentTimeMillis() - start));
    }

    private static void multiThreadSum(long[] arr) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        int count = 8;
        ExecutorService threadPool = Executors.newFixedThreadPool(count);
        ArrayList<Future> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int num = i ;
            Future<Long> future = threadPool.submit(()->{
                long sum = 0;
                for (int j = arr.length / count * num; j < (arr.length / count * (num - 1)); j++) {
                    try {
                        sum += (arr[j] / 3 * 3 / 3 * 3 / 3 * 3 / 3 * 3 / 3 * 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return sum;
            });
            list.add(future);
        }

        //ÿ���ν�����
        long sum = 0 ;
        for (Future<Long> future : list) {
            sum += future.get();
        }

        System.out.println("sum = " + sum);
        System.out.println("multi thread elapse : " + (System.currentTimeMillis() - start));
    }

    private static void forkJointSum(long arr[]) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        //�ύ����
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(new SumTask(arr, 0, arr.length));
        //��ȡ���
        Long sum = forkJoinTask.get();
        forkJoinPool.shutdown();

        System.out.println("sum = " + sum);
        System.out.println("fork join elapse : " + (System.currentTimeMillis() - start));
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
            if (to - from <= 1000) {
                long sum = 0;
                for (int i = from; i < to; i++) {
                    sum += (arr[i] / 3 * 3 / 3 * 3 / 3 * 3 / 3 * 3 / 3 * 3);
                }
                return sum;
            }

            //�ֳ���������
            int middle = (from + to) /2 ;
            SumTask left = new SumTask(arr, from, middle);
            SumTask right = new SumTask(arr, middle, to);
            //�ύ��ߵ�����
            left.fork();
            Long rightResult = right.compute();
            //�ȴ���߼������
            Long leftResult = left.join();
            //���ؽ��
            return leftResult + rightResult;
        }
    }



}
