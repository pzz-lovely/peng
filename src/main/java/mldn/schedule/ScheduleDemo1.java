package mldn.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Auther lovely
 * @Create 2020-03-24 21:19
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
//继承 TimerTask
class TaskThread extends TimerTask{
    @Override
    public void run() {
        System.out.println("{定时任务调用}0.0");
    }
}
public class ScheduleDemo1 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TaskThread(), 1000, 2000);

    }
}




