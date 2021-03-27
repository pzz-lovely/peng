package one.chapter12.toolBar;

import one.chapter12.factory.FrameFactory;

/**
 * @Auther lovely
 * @Create 2020-02-25 15:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ToolBartTest {
    public static void main(String[] args) {
        FrameFactory.runFrame(new ToolBarFrame(),"ToolBar demo");
    }
}
