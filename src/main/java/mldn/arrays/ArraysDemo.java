package mldn.arrays;

import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-03-24 22:13
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ArraysDemo {
    public static void main(String[] args) {
        int data[] = new int[]{3, 423, 7, 4, 64, 35,9, 45, 60};
        Arrays.sort(data);
        System.out.println(search(data,9));
    }


    /**
     * 本方法的主要作用是查找在指定的数据之中是否存在有指定的数组内容
     * 如果存在则返回相应的索引，如果不存在返回-1
     * @param data 要查找的数组内容
     * @param key 要查找的数据
     * @return 数组索引，如果没有找到返回-1
     */
    public static int search(int data[], int key) {
        int low = 0;//开始索引
        int high = data.length-1;   //结束索引
        //现代的语言设计结构，强调通过循环结构来代替递归结构，这样可以提升性能
        while (low <= high) {
            //进行中间索引的确定，折半
            int mid = (low+high) >>> 1;
            int midVal = data[mid];
            if (midVal < key) {
                low =mid+1;
            } else if (midVal > key) {
                high =mid -1;
            }else{
                return mid;
            }
        }
        return -(low + 1);
    }
}
