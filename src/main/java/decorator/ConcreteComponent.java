package decorator;

/**
 * ����������
 */
public class ConcreteComponent extends Component {
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        System.out.println("�������Ĳ���");
    }
}
