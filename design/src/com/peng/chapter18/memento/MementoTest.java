package com.peng.chapter18.memento;

/**
 * @Auther lovely
 * @Create 2020-04-29 13:43
 * @Description
 * 备忘录(Memento)：在不破化封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态 。
 */
public class MementoTest {
    public static void main(String[] args) {
        /*发起人*/
        Originator originator = new Originator();
        originator.setState("On");
        originator.show();
        /*管理者 管理memento*/
        Caretaker c = new Caretaker();
        c.setMemento(originator.createMemento());

        originator.setState("Off");
        originator.show();

        originator.setMemento(c.getMemento());
        originator.show();

    }
}
