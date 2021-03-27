package juc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum+=i;
        }
        return sum;
    }
}

public class TestCallable {
    public static void main(String[] args) {
        CallableDemo demo = new CallableDemo();
        FutureTask<Integer> result = new FutureTask<Integer>(demo);
        new Thread(result).start();
        Integer sum = null;
        try{
            sum = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("结果为:"+sum);
    }
}
