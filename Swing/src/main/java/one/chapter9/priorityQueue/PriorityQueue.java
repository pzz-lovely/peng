package one.chapter9.priorityQueue;

import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-03-11 14:58
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class PriorityQueue {

    private int[] array;
    private int size;
    public PriorityQueue(){
        //���г�ʼ����32
        array = new int[32];
    }

    /**
     * ���
     * @param key ���Ԫ��
     */
    public void enQueue(int key) {
        //���г��ȳ�����Χ������
        if(size >= array.length){
            resize();
        }
        array[size++] = key;
        upAdjust();
    }
    /**
     * ����
     */
    public int deQueue() throws Exception {
        if(size <= 0){
            throw new Exception("the queue is empty !");
        }
//��ȡ�Ѷ�Ԫ��
        int head = array[0];
//���һ��Ԫ���ƶ����Ѷ�
        array[0] = array[--size];
        downAdjust();
        return head;
    }
    /**
     * �ϸ�����
     */
    public void upAdjust() {
        int childIndex = size-1;
        int parentIndex = childIndex/2;
// temp��������Ҷ�ӽڵ�ֵ���������ĸ�ֵ
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex])
        {
//������������������ֵ����
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }
    /**
     * �³�����
     */
    public void downAdjust() {
// temp���游�ڵ�ֵ���������ĸ�ֵ
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
// ������Һ��ӣ����Һ��Ӵ������ӵ�ֵ����λ���Һ���
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
// ������ڵ�����κ�һ�����ӵ�ֵ��ֱ������
            if (temp >= array[childIndex])
                break;
//������������������ֵ����
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }
    /**
     * �³�����
     */
    public void resize() {
//������������
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }
    public int[] toArray(){
        return array;
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        /*System.out.println("����Ԫ�أ�" + priorityQueue.deQueue());
        System.out.println("����Ԫ�أ�" + priorityQueue.deQueue());*/
        System.out.println(Arrays.toString(priorityQueue.toArray()));
    }
}
