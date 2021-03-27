package one.chapter10.font;

import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 16:02
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FontFamily {
    public static void main(String[] args) {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String s: fontNames
             ) {
            System.out.println(s);
        }
    }
}
