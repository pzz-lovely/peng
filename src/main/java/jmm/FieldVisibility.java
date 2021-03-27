package jmm;


import com.sun.org.apache.bcel.internal.generic.NEW;

import static sun.misc.Version.print;

/**
 * @Auther lovely
 * @Create 2020-03-14 17:40
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FieldVisibility {
    private boolean flag = true;
    public static void main(String[] args) {
        FieldVisibility fieldVisibility = new FieldVisibility();
        Thread thread1 = new Thread(()->{
            System.out.println(fieldVisibility.flag);
            while (fieldVisibility.flag) {
                /*System.out.println(fieldVisibility.flag);*/
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fieldVisibility.flag = false;
            System.out.println("2 :"+fieldVisibility.flag);
        });
        thread1.start();
        thread2.start();
    }
}
