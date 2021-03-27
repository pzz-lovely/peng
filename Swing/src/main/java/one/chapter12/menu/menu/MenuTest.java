package one.chapter12.menu.menu;

import one.chapter12.factory.FrameFactory;

/**
 * @Auther lovely
 * @Create 2020-02-25 15:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MenuTest {
    public static void main(String[] args) {
        FrameFactory.runFrame(new MenuFrame(),"Menu demo");
    }
}
