package mldn.runtime;

/**
 * @Auther lovely
 * @Create 2020-03-22 21:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RuntimeDemo2 {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("maxMemory之前"+runtime.maxMemory());
        System.out.println("totalMemory之前"+runtime.totalMemory());
        System.out.println("freeMemory之前"+runtime.freeMemory());
        /*以上的代码返回都是字节，在java程序中，所有与文件大小有关的操作都会以long数据类型 定义
        *
        * 以上返回的内存都是
        * 整个JVM进程的可用最大内存(MaxMemory) > 默认的可用内存(totalMemory，这个值是会改变的，但是最终不超过MaxMemory) >空闲内存(FreeMemory)
        * ，如果空闲内存不足则TotalMemory也会进行动态扩充(这种动态扩充实际上是非常伤害程序的)
        *
        * */

        String message = "0.0";
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            message += i + "\n";
        }
        System.out.println("maxMemory之后"+runtime.maxMemory());
        System.out.println("totalMemory之后"+runtime.totalMemory());
        System.out.println("freeMemory之后"+runtime.freeMemory());

        /*
        * 如果此时内存空间被垃圾空间严重沾满了，并且来不及进行扩充，那么就会出现如下异常信息，
        * OutOfMemoryError OOM内存溢出
        *
        * */
    }
}
