package com.peng.chapter19;

/**
 * @Auther lovely
 * @Create 2020-04-29 18:03
 * @Description
 * Leaf在组合中表示叶节点对象，叶节点没有子节点
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("Cannot add to a leaf");
    }

    @Override
    public void remove(Component component) {
        System.out.println("Cannot remove from a leaf");
    }

    @Override
    public void display(int depth) {
        System.out.println(new String("-" + depth) + name);
    }
}
