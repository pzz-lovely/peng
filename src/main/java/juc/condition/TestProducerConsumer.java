package juc.condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Food{ //食物
    private String id;

    public Food(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "产品"+id;
    }
}
class SyncStack{
    private int index = 0 ;
    private Food[] foods = new Food[6];
    private Lock lock = new ReentrantLock();    //重入锁
    private Condition condition = lock.newCondition();
    public SyncStack(){}

    /**
     * 添加事物的方法,首先获取锁，如果装食物的容器满了就 condition.await()睡眠,不满添加食物 唤醒其他其他等待线程 condition.signalAll();
     * @param food 事物
     */
    public void push(Food food) {
        lock.lock();        //设置锁
        try{
            while (index == foods.length) {
                try{
                    System.out.println("容器已满");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            foods[index] = food;
            index++;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 取出的方法,容器为空 condition.await() 不为空 condition.signalAll()
     * @return
     */
    public Food pop(){
        lock.lock();
        try{
            while (index == 0) {
                try{
                    System.out.println("容器以空");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            index--;
            condition.signalAll();
        }finally{
            lock.unlock();
        }
        return foods[index];
    }
}

class Producer implements Runnable {
    private SyncStack ss;

    public Producer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Food f = new Food(Thread.currentThread().getName() + i);
            ss.push(f);
            System.out.println("生成者" + Thread.currentThread().getName() + "生产了 " + f);
            try{
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable{
    private SyncStack ss;
    public Consumer(SyncStack ss){this.ss=ss;}

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Food food = ss.pop();
            System.out.println("消费者 " + Thread.currentThread().getName() + "消费了 " + food);
            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestProducerConsumer {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        new Thread(new Producer(ss), "A").start();
        new Thread(new Producer(ss), "B").start();
        new Thread(new Consumer(ss), "C").start();
        new Thread(new Consumer(ss), "D").start();
    }
}
