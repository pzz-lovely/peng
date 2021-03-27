package background;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-03-12 15:19
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 观察者模式
 */
public class MultiThreadError5 {
    int count;

    public MultiThreadError5(MySource mySource) {
        mySource.registerListener(new EventListener() {//持有当前对象
            @Override
            public void onEvent(Event event) {
                System.out.println("\n我得到的数字是:" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(()->{
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {});
        }).start();
        MultiThreadError5 m = new MultiThreadError5(mySource);
    }

    static class MySource{
        private EventListener listener;
        void registerListener(EventListener listener) {
            this.listener = listener;
        }

        void eventCome(Event event) {
            if (listener != null) {
                listener.onEvent(event);
            }else{
                System.out.println("还未初始化");
            }
        }
    }



    interface EventListener{
        void onEvent(Event event);
    }

    interface Event{
    }
}
