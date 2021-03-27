package mldn.concurrent.arrays;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList是基于数组实现的并发集合访问类，在使用此类进行数据"添加/修改/删除"操作时都会创建一个新的数组，并将更新后的数据复制到新建的数组。CopyOnWriteArrayList类性能不高，但是数据遍历查找时性能会比较高
 * 使用CopyOnWriteArrayList实现多线程并发访问
 *
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        List<String> all = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int x = 0; x < 10; x++) {
                    all.add(Thread.currentThread().getName() + " 0.0 "+x+"\n");
                }
            }," 集合操作线程 "+i).start();
        }
        System.out.println(all.size());
        System.out.println(all.toString());
    }
}
