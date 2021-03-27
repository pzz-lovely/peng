package observed;

/**
 * 基于subject被观察者的需求，这个是实现一个基本的subject接口
 */
public interface Subject {
    void register(Observer obj);

    void unregister(Observer obj);

    void notifyObservers();

    Object getUpdate(Observer obj);
}
