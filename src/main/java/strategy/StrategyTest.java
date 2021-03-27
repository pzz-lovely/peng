package strategy;


/**
 * 策论模式
 *  定义一个上下文 context类 里面定一个具体实现的方法 方法参数为类
 *  程序则可以从context类中的方法 实现多个样式
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
