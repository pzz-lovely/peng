package strategy;

/**
 * ������
 */
public class Context {
    Strategy strategy;
    public Context( Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * �����Ľӿ�
     */
    public void ContextInterface(){
        strategy.algorithmInterface();
    }

}
