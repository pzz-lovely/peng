package mldn.lined3;

public interface Linked<E> {
    void add(E data);

    boolean isEmpty();

    int size();

    void set(int index, E data);

    void get(int index);

    Object[] toArray();

    boolean containsData(E data);

    void remove(E data);
}
