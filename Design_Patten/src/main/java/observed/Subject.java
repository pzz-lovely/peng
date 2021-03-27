package observed;

/**
 * ����subject���۲��ߵ����������ʵ��һ��������subject�ӿ�
 */
public interface Subject {
    void register(Observer obj);

    void unregister(Observer obj);

    void notifyObservers();

    Object getUpdate(Observer obj);
}
