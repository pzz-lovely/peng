package strategy;


/**
 * ����ģʽ
 *  ����һ�������� context�� ���涨һ������ʵ�ֵķ��� ��������Ϊ��
 *  ��������Դ�context���еķ��� ʵ�ֶ����ʽ
 */
public class StrategyTest {
    public static void main(String[] args) {
        Context context = null;

        context = new Context(new ConcreteStrategyA());
        context.ContextInterface();

        context = new Context(new ConcreteStrategyB());
        context.ContextInterface();

        context = new Context(new ConcreteStrategyC());
        context.ContextInterface();

    }
}
