package chapter1;

/**
 * @Auther lovely
 * @Create 2020-03-28 8:46
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 序列生成器
 */
public class Sequence {
    private int value;

  /*  *//**
     * 线程不安全的，存在这 多个线程 竟态条件
     * 多个线程 并发的执行 读改写
     * @return
     *//*
    public int getValue(){
        return value++;
    }*/

    /**
     * 线程安全
     * @return
     */
    public int getValue(){
        return value++;
    }
}
