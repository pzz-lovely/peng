package mldn.map;

import mldn.binaryTree.Book;

/**
 * @Auther lovely
 * @Create 2020-04-01 17:01
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class IMapTest {
    public static void main(String[] args) {
        IMap<String, Book> map = new BinaryTreeMapImpl<>();
        System.out.println("save data : "+map.put("java", new Book("java从入门到精通", "0.0", 98.8)));
        System.out.println("save data : "+map.put("python", new Book("python从入门到精通", "0.1", 88.8)));
        System.out.println("save data : "+map.put("javaWeb", new Book("javaWeb从入门到精通", "0.3", 108.8)));
        System.out.println(map.get("python"));
    }
}
