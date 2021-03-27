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
        //��ʼ����
        array = new int[32];
    }


    /**
     * ���
     * @param key ���Ԫ��
     */
    private void enQueue(int key) {
        //���г��ȳ�����Χ������
        if (size >= array.length) {
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * ����
     * @return
     * @throws Exception
     */
    private int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty");
        }
        //��ȡ�Ѷ�Ԫ��
        int head = array[0];
        //���һ��Ԫ���ƶ����Զ�
        array[0] = array[--size];
        downAdjust();
        return head;
    }


    /**
     * �ϸ�����
     */
    private void upAdjust(){
        int childIndex = size-1;
        int parentIndex = childIndex / 2;
        //temp��������Ҷ�ӽڵ�ֵ���������ĸ�ֵ
        int temp = array[childIndex]; //������ �ӵ� ֵ
        while (childIndex > 0 && temp > array[parentIndex]) {
            //���������Ľ��������ֵ����
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;    //�����ڵ��λ�� ���� �� �����ڵ� �޷� ���� 0 1
            parentIndex = parentIndex/2;
        }
        array[childIndex] = temp;
    }

    /**
     * �³�����
     */
    private void downAdjust(){
        //temp���游�ڵ�ֵ���������ĸ�ֵ
        int parentIndex = 0;
        int temp = array[parentIndex];  //���ڵ�
        int childIndex = 1;
        while (childIndex < size) {
            //������Һ��ӣ����Һ��Ӵ������ӵ�ֵ����λ���Һ���
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            //������ڵ�����κ�һ�����ӵ�ֵ��ֱ������
            if(temp >= array[childIndex])
                break;
            //�����������������ֵ����
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex+1;
        }
        array[parentIndex] = temp;
    }
    /**
     * �³�����
     */
    private void resize(){
        //������������
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
   /*     System.out.println("����Ԫ�أ�" + queue.deQueue());
        System.out.println("����Ԫ�أ�" + queue.deQueue());*/
        System.out.println(Arrays.toString(queue.toArray()));
    }

}
