package thread;

import java.util.Arrays;
import java.util.List;

/**
 * ���м���,������߳������е�Ч�ʣ����̲߳���ִ��
 */
public class CreateThread04 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //����
        list.stream().forEach(System.out::print);
        System.out.println();
        //����
        list.parallelStream().forEach(System.out::print);
    }
}
