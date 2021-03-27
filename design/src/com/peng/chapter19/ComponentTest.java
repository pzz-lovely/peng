package com.peng.chapter19;

/**
 * @Auther lovely
 * @Create 2020-04-29 18:55
 * @Description todo
 */
public class ComponentTest {
    public static void main(String[] args) {
        Composite root = new Composite("root");
        root.add(new Leaf("LeafA"));
        root.add(new Leaf("LeafB"));

        Composite comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf A"));
        comp.add(new Leaf("Leaf B"));
        root.add(comp);


        Composite comp2 = new Composite("Composite XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));
        comp.add(comp2);

        root.add(new Leaf("Leaf C"));

        Leaf leaf = new Leaf("Leaf D");
        root.add(leaf);
        root.remove(leaf);

        root.display(1);
    }
}
