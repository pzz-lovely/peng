package chapter3.decorator;

public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("具体的操作对象");
    }
}
