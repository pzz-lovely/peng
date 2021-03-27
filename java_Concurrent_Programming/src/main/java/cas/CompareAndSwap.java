package cas;

public class CompareAndSwap {
    private int value;
    public synchronized int get(){
        return value;
    }
}
