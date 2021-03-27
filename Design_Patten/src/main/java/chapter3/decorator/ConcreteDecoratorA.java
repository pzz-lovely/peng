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
        System.out.println("����װ�ζ���A�Ĳ���");
    }
}
