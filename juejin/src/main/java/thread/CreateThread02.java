package thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ��ʱ�� java.util.Timer
 * ʹ�ö�ʱ��java.util.Timer���Կ��ٵ�ʵ�ֶ�ʱ����timerTaskʵ����ʵ����Runnable�ӿ�
 */
public class CreateThread02 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //ÿ��һ��ִ��һ��
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        },1000,2000);
    }
}
