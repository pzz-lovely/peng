package strategy;

public class ConcreteStrategyA extends Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("算法a实现");
    }
}
class ConcreteStrategyB extends Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("算法b实现");
    }
}

class ConcreteStrategyC extends Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("算法c实现");
    }
}
