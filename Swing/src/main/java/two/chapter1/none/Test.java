package two.chapter1.none;

/**
 * @Auther lovely
 * @Create 2020-02-28 16:10
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        //���ʾ�̬����
        IConvert<String, String> convert1 = Something::startWith;
        System.out.println(convert1.convert("123"));


        //���ʶ��󷽷�
        Something something = new Something();
        IConvert<String, String> convert2 = something::endWith;
        System.out.println(convert2.convert("123"));

        //���ʹ��췽��
        IConvert<String, Something> convert = Something::new;
        System.out.println(convert.convert("constructors"));
/*
        �ܽ᣺���ǿ��԰���Something�еķ���static String startsWith(String s){...}��String endWith(String s){...}��Something(String something){...}�����ǽӿ�IConvert��ʵ�֣���Ϊ���Ƕ����Ͻӿڶ�����Ǹ���ģ�桱���д�������F�Լ�����ֵ����T�����繹�췽��Something(String something)��������ΪString���ͣ�����ֵ����ΪSomething��ע��@FunctionalInterface��֤�˽ӿ����ҽ���һ�����󷽷�������JDK��׼ȷ��ƥ�䵽��Ӧ������


        �ܽ�:���ǿ��԰���Something�еķ���static String startWith(String s){}��String endWidth(String s){}��Something(String
        something){} �����ǽӿ�IConvert��ʵ�֣���Ϊ���Ǹ��Ͻӿڶ�����Ǹ�ģ�壬�д�������F�Լ�����ֵ����T�����繹�췽��Something(String something),
        ������ΪString���ͣ�����ֵΪSomething��ע��@FunctionInterface��֤�˽ӿ����ҽ���һ�����󷽷�
*/

    }
}
