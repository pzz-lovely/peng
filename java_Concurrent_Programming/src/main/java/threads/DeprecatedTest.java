package threads;

import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 过期的suspend()，resume()和stop()
 */
public class DeprecatedTest {
    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        SleepUtils.second(3);
        //将printThread进行暂停，输出内容工作停止
        printThread.suspend();
        System.out.println("main suspend PrintThread at" + format.format(new Date()));
        SleepUtils.second(1);
        
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName()+" Run at "+format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
