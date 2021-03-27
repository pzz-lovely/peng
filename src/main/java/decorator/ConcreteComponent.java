package decorator;

/**
 * 混凝土构件
 */
public class ConcreteComponent extends Component {
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
