package one.chapter9.priorityQueue;

import org.omg.CORBA.ARG_IN;

import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-03-11 14:59
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ImplementationPriorityQueue {
    private int[] array;
    private int size;
    private ImplementationPriorityQueue(){
        //初始长度
        array = new int[32];
    }


    /**
     * 入队
     * @param key 入队元素
     */
    private void enQueue(int key) {
        //队列长度超出范围，扩容
        if (size >= array.length) {
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     * @return
     * @throws Exception
     */
    private int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty");
        }
        //获取堆顶元素
        int head = array[0];
        //最后一个元素移动到对顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }


    /**
     * 上浮调整
     */
    private void upAdjust(){
        int childIndex = size-1;
        int parentIndex = childIndex / 2;
        //temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex]; //保存了 子的 值
        while (childIndex > 0 && temp > array[parentIndex]) {
            //无需真正的交换，单项赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;    //将父节点的位置 赋给 子 而父节点 无非 就是 0 1
            parentIndex = parentIndex/2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void downAdjust(){
        //temp保存父节点值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];  //父节点
        int childIndex = 1;
        while (childIndex < size) {
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            //如果父节点大于任何一个孩子的值，直接跳出
            if(temp >= array[childIndex])
                break;
            //无需真正交换，单项赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex+1;
        }
        array[parentIndex] = temp;
    }
    /**
     * 下沉调整
     */
    private void resize(){
        //队列容量翻倍
        int newSize = this.size *2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public int[] toArray(){
        return array;
    }


    public static void main(String[] args) throws Exception {
        ImplementationPriorityQueue queue = new ImplementationPriorityQueue();
        queue.enQueue(4);
        queue.enQueue(2);
        queue.enQueue(3);
/*        queue.enQueue(7);
        queue.enQueue(2);
        queue.enQueue(5);*/
   /*     System.out.println("出队元素：" + queue.deQueue());
        System.out.println("出队元素：" + queue.deQueue());*/
        System.out.println(Arrays.toString(queue.toArray()));
    }

}
