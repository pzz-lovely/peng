package chapter4.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    //保证所有ConnectionRunner能够同时开始
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    static CountDownLatch end ;


    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        countDownLatch.countDown();
        end.await();
        System.out.println("total invoke : " + (threadCount * count));
        System.out.println("got connection : " + got);
        System.out.println("not got connection : "+notGot);
    }

    static class ConnectionRunner implements Runnable {
        int count ;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try{
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try{
                    //从连接池中获取连接，如果1000ms内无法获取到，将会返回Null
                    //分别统计连接的数量got和为获取到的notGot
                    Connection connection = pool.fetchConnection(1000);
                    connection.commit();
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }finally{
                    count--;
                }
            }
            end.countDown();
        }
    }
}

