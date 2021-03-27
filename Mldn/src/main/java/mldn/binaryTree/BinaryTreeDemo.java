package mldn.binaryTree;

import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-03-31 19:54
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        /*IBinaryTree<Book> binaryTree = new BinaryTreeImpl<>();
        binaryTree.add(new Book("Java从入门到项目实 战","0.0",99.8));
        binaryTree.add(new Book("Python从入门到项目实战","0.0",97.8));
        binaryTree.add(new Book("Go从入门到项目实战","0.0",99.9));
        System.out.println("元素个数:"+binaryTree.size());
        System.out.println(Arrays.toString(binaryTree.toArray()));
        System.out.println(binaryTree.contains(new Book("Java从入门到项目实战","0.0",99.8)));
        System.out.println(binaryTree.contains(new Book("JavaWeb从入门到项目实战","0.0",89.8)));*/
        IBinaryTree<Integer> binaryTree = new BinaryTreeImpl<>();
    }
}
