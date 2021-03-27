package threadcoreknowledge.volatiledemo;

import java.util.concurrent.BlockingQueue;

/**
 * @Auther lovely
 * @Create 2020-03-07 10:17
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示volatile的局限
 *陷入阻塞中 volatile是无法更改的
 * 此例中，生产者的生成速度快，消费者消费速度慢
 *
 */
public class WrongWayVolatileCantStop implements Runnable {
    public static void main(String[] args) {

    }

    @Override
    public void run() {

    }
}


