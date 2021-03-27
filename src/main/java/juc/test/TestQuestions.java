package juc.test;

class Number{
    public static synchronized void getOne(){
        try{
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public synchronized void getTwo(){
        System.out.println("two");
    }
    public void getThree(){
        System.out.println("three");
    }
}
public class TestQuestions {
    public static void main(String[] args) {
        Number number = new Number();
        Number number1 = new Number();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//				number.getTwo();
                number1.getTwo();
            }
        }).start();
    }

}
