package mldn.concurrent.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue类属于单端阻塞队列，所有的数据将按照FIFO算法进行保存于获取，BlockingQueue类提供以下几个子类:ArrayBlockingQueue(数组结构)、LinkedBlockingQueue(链表单端阻塞队列)、PriorityBlockingQueue(优先级阻塞队列)、SynchronousQueue(同步队列)
 * Blocking是一个利用属猪控制形式实现的队列操作，需要在其实例化时直接提供数组的长度，也可以设置阻塞线程的公平与非公平抢占原则。
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int x = 0; x < 100; x++) {
                }
            }).start();
        }
    }
}
