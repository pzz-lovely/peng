package one.chatpter14.swing;

import one.chapter12.factory.FrameFactory;

/**
 * @Auther lovely
 * @Create 2020-02-27 16:17
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SwingThreadTest {
    public static void main(String[] args) {
        FrameFactory.runFrame(new SwingThreadFrame(),"Swing Thread test");
    }
}
