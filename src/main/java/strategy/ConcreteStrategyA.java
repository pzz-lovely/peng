package strategy;

public class ConcreteStrategyA extends Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("�㷨aʵ��");
    }
}
class ConcreteStrategyB extends Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("�㷨bʵ��");
    }
}

class ConcreteStrategyC extends Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("�㷨cʵ��");
    }
}
