package decorator;

public class ConcreteDecoratorA extends Decorator {
    private String addedState;  //本类独有的功能，区别ConcreteDecorator

    @Override
    public void operation() {
        super.operation();
        addedState = "new State";
        System.out.println("具体装饰对象A的操作");
    }
}

class ConcreteDecoratorB extends Decorator {

    @Override
    public void operation() {
        super.operation();
        AddBehavior();
        System.out.println("0.0");
    }

    private void AddBehavior(){
        System.out.println("具体装饰对象B的操作");
    }
}
