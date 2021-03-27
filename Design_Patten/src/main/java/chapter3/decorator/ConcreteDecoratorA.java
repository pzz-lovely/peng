package chapter3.decorator;

public class ConcreteDecoratorA extends Decorator {
    private String addedState;

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedState = "new State";
        System.out.println("具体装饰对象A的操作");
    }
}
