package logging;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Auther lovely
 * @Create 2020-02-10 10:25
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description  A modification of the image viewer program that logs various events
 */
public class LoggingImageViewer {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null && System.getProperty("java.util.logging" +
                ".config.file") == null) {
            try{
                Logger.getLogger("com.peng.corejava").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("com.peng.corejava").addHandler(handler);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EventQueue.invokeLater(()->{
            Handler windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.peng.corejava").addHandler(windowHandler);
            JFrame frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("com.peng.corejava").fine("Showing frame");
            frame.setVisible(true);
        });
    }
}
