package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 */
public class PriorityTest {
    private static volatile boolean notStart = true;    //开头
    private static volatile boolean notEnd = true;      //结尾
    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread - " + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(2);
        notEnd = false;
        for (Job job : jobs) {
            System.out.println("Job priority " + job.priority + " Count " + job.jobCount);
        }


    }
    static class Job implements Runnable{
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while(notStart){
                Thread.yield(); //线程礼让
            }
            while(notEnd){
                Thread.yield(); //线程礼让
                jobCount++;
            }
        }
    }

}
