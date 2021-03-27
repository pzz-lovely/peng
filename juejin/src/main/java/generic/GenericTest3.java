package generic;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * java��ķ����Ǽٷ��ͣ�ֻ�ڱ�������Ч��������ʱ��û�з��͵ĸ����
 *
 * java�еķ���ֻ�ڱ���������Ч��������ʱֻ�е�����֪�����Լ���Ҫʲô���ͣ��������ߵ��÷��ͷ����Լ���ǿ��ת����������������ȫ�޸ɸеģ���������ֻ�ܾ����õ�����֪��������
 */
public class GenericTest3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> strList = new ArrayList<>();
        Method addMethod = strList.getClass().getMethod("add", Object.class);
        addMethod.invoke(strList, 1);
        addMethod.invoke(strList, true);
        addMethod.invoke(strList, new Long(1));
        addMethod.invoke(strList, new Byte[]{1});
        //��ӡ [1, true, 1, 1]
        System.out.println(strList);

    }
}
