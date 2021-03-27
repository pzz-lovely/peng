package com.peng.maketea;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * @Author lovely
 * @Create 2020-11-12 16:38
 * @Description todo
 */
public class MakeTeaFutureDemo {
    public static final int SLEEP_GAP = 500;
    private static Logger logger = Logger.getLogger(MakeTeaFutureDemo.class.getSimpleName());

    public static String getCurThreadName(){
        return Thread.currentThread().getName();
    }


    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                logger.info("洗好水壶");
                logger.info("灌上凉水");
                logger.info("放在火上");

                // 线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                logger.info("水开了");
            } catch (InterruptedException e) {
                logger.info("发生了异常被中断");
                return false;
            }
            logger.info("烧水运行结束");
            return true;
        }

    }
    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                logger.info("洗水壶");
                logger.info("洗茶杯");
                logger.info("拿茶叶");
                // 线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                logger.info("洗完了");
            } catch (InterruptedException e) {
                logger.info("清洗工作发生异常被中断");
                return false;
            }
            logger.info("清洗工作运行结束");
            return true;
        }
    }

    public static void drinkTea(boolean waterOk, boolean cupOk) {
        if (waterOk && cupOk) {
            logger.info("泡茶喝");
        } else if (!waterOk) {
            logger.info("烧水失败，没有茶喝了");

        } else if (!cupOk) {
            logger.info("烧水失败，没有茶喝了");

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Callable<Boolean> hJob = new HotWaterJob();
        Future<Boolean> hTask = service.submit(hJob);

        Callable<Boolean> wJob = new WashJob();
        Future<Boolean> wTask = service.submit(wJob);

        boolean waterOk = hTask.get();
        boolean cupOk = wTask.get();
        drinkTea(waterOk, cupOk);
    }
}