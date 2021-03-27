package chapter3.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA a = new ConcreteDecoratorA(c);
        ConcreteDecoratorB b = new ConcreteDecoratorB(c);
        a.operation();
        b.operation();
    }

}
