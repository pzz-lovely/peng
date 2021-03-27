package threads;

/**
 * 如果一个线程A执行了thread.join()语句:当前线程A等待thread线程终止之后才从thread.join()返回
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Domino domino = new Domino(previous);
            Thread thread = new Thread(domino, " 节点线程" + i + "前一个节点 " + domino.thread);
            thread.start();
            previous = thread;
        }
        SleepUtils.second(5);
        System.out.println(Thread.currentThread().getName()+" terminate.");
    }

    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try{
                //线程加入
                System.out.println("线程加入");
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" terminate.");
        }
    }
}
