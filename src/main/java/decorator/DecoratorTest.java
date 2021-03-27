package decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA A = new ConcreteDecoratorA();
        ConcreteDecoratorB B = new ConcreteDecoratorB();

        A.setComponent(c);
        B.setComponent(A);

        B.operation();
    }
}
