package generic;

import java.util.Arrays;
import java.util.List;

/**
 * java��ķ����Ǽٷ��ͣ�ֻ�ڱ�������Ч��������ʱ��û�з��͵ĸ���
 */
public class GenericTest2 {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("1");
        List<Integer> intList = Arrays.asList(1);
        //��ӡtrue
        System.out.println(strList.getClass() == intList.getClass());
    }
}
