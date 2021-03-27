package com.peng.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Auther lovely
 * @Create 2020-05-24 21:06
 * @Description todo
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 连接释放后需要进行通知，这样其他消费能够感知到连接池已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool) {
            // 完全超时
            if (mills <= 0) {
                // 有连接就返回，没数据永久等待
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                // 即将到期的时间
                long future = System.currentTimeMillis() + mills;
                // 要等待的时间
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    // 等待一段时间后，再来看看有没有连接
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                // 不为空
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
