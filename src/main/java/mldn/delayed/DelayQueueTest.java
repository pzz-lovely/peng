package mldn.delayed;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 在JUC中提供自动弹出数据的延迟队列DelayQueue，该类属于BlockingQueue接口子类，而对于延迟操作的计算则需要通过Delayed接口进行计算
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Student> queue = new DelayQueue<Student>();
        queue.put(new Student("小李", 2, TimeUnit.SECONDS));
        queue.put(new Student("小张", 5, TimeUnit.SECONDS));
        while (!queue.isEmpty()) {
            Student stu =queue.take();
            System.out.println(stu);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

class Student implements Delayed {
    private String name;
    private long expire;        //离开时间
    private long delay;         //提留时间

    public Student(String name, long delay, TimeUnit timeUnit) {
        this.name = name;
        this.delay = TimeUnit.MICROSECONDS.convert(delay, timeUnit);//转换W为毫秒
        this.expire = System.currentTimeMillis()+delay;
    }

    @Override
    public String toString() {
        return this.name+"同学已经带到了预计的停留时间"+TimeUnit.SECONDS.convert(this.delay,TimeUnit.MILLISECONDS)+"秒,已经离开了";
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.delay - this.getDelay(TimeUnit.MILLISECONDS));
    }
}
