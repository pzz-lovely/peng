package exchanges;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;

public class ExchangerTest1 {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();    //定义交换空间
        boolean isEnd = false;
        new Thread(()->{
            String data = null;
            try{
                for (int i = 0; i < 2; i++) {
                    data = "0.0"+i;
                    System.out.println("产生 - Before " + data);
                    Thread.sleep(1000);
                    exchanger.exchange(data);
                    System.out.println("产生 after" + data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"信息产生者 ").start();
        new Thread(() ->{
            String data = null;
            try{
                while (!isEnd) {
                    System.out.println("消费 before " + data);
                    Thread.sleep(1000);
                    data = exchanger.exchange(null);
                    System.out.println("消费 after "+data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"信息消费者").start();
    }
}
