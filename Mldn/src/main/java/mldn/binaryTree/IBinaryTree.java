package mldn.binaryTree;

/**
 * @Auther lovely
 * @Create 2020-03-31 19:53
 * @PACKAGE_NAME
 * @Description 定义二叉树的标椎接口
 */
public interface IBinaryTree<E> {
    /**
     * 数据的增加
     * @param data
     */
    public void add(E data);

    public int size();

    public Object[] toArray();

    boolean contains(E data);

    void remove(E data);
}
