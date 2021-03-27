package decorator;

public class ConcreteDecoratorA extends Decorator {
    private String addedState;  //������еĹ��ܣ�����ConcreteDecorator

    @Override
    public void operation() {
        super.operation();
        addedState = "new State";
        System.out.println("����װ�ζ���A�Ĳ���");
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
        System.out.println("����װ�ζ���B�Ĳ���");
    }
}
