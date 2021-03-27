package one.chapter12.border;

import one.chapter12.factory.FrameFactory;

/**
 * @Auther lovely
 * @Create 2020-02-24 20:57
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BorderTest {
    public static void main(String[] args) {
        FrameFactory.runFrame(new BorderFrame(),"demo border");
    }
}
