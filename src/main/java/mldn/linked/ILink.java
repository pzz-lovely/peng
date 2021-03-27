package mldn.linked;

public interface ILink<E> {
    /**
     * 向链表中添加新数据
     * @param e
     */
    void add(E e);

    /**
     * 获取数据的长度
     * @return
     */
    int size();

    /**
     * 判断集合是否为空，只要是依据长度判断
     * @return
     */
    boolean isEmpty();

    /**
     * 将集合以对象数组的形式返回
     * @return
     */
    E[] toArray();

    /**
     * 根据索引获取指定数据
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 修改指定索引数据
     * @param index
     * @param data
     */
    void set(int index, E data);

    /**
     * 判断数据是否存在，需要equals()对象比较方法的支持
     * @param data
     * @return
     */
    boolean contains(E data);

    /**
     * 删除数据，需要equals()对象比较方法的支持
     * @param e
     */
    void remove(E e);

    /**
     * 清空集合，将根元素清空
     */
    void clean();
}
