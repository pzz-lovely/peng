package one.chapter9.priorityQueue;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther lovely
 * @Create 2020-02-20 11:01
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(2010, 12, 9));
        pq.add(LocalDate.of(2000, 1, 3));
        pq.add(LocalDate.of(2001, 4, 25));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elements");
        for(LocalDate date : pq)
            System.out.println(date);
        System.out.println("Removing elements");
        while(!pq.isEmpty())
            //取出元素
            System.out.println(pq.remove());
        System.out.println(pq.isEmpty());
        System.out.println("aa : "+Integer.compare(5, 3));

        /*测试不实现 comparable接口 有没有用 测试结果 必须实现 Comparable接口*/
        PriorityQueue<Man> man = new PriorityQueue<>(8/*,PriorityQueueTest::compare*/);
        man.add(new Man("0.3", 17));
        man.add(new Man("0.0", 30));
        man.add(new Man("0.2", 15));
        //man.add(new Man("0.1", 20));
       /* System.out.println(man.poll());
        System.out.println(man.poll());*/
        System.out.println(man);
    }


    public static int compare(Man o1, Man o2){
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
