package com.peng.chapter19;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther lovely
 * @Create 2020-04-29 18:05
 * @Description
 * Composite定义有枝节点行为，用来存储子部件
 */
public class Composite extends Component {

    private List<Component> components = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        component.add(component);
    }

    @Override
    public void remove(Component component) {
        component.remove(component);
    }

    @Override
    public void display(int depth) {
        components.forEach(e -> {
            e.display(2);
        });
    }
}
