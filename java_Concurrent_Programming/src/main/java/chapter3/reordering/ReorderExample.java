package chapter3.reordering;

/**
 * 重排序对 多线程的影响
 */
public class ReorderExample {
    int a = 0;
    boolean flag = false;
    public void writer(){
        a =  1;
        flag = true;
    }

    public void reader(){
        if (flag) {
            int i = a * a;
        }
    }


    public static void main(String[] args) {
        ReorderExample reorderExample = new ReorderExample();
        Thread writeThread = new Thread(()->{
            reorderExample.writer();
            System.out.println("write :"+reorderExample.a);
        });

        Thread readThread = new Thread(()->{
            reorderExample.reader();
            System.out.println("read : "+reorderExample.a);
        });
        writeThread.start();
        readThread.start();
    }
}
