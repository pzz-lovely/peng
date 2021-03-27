package logging;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * @Auther lovely
 * @Create 2020-02-10 10:53
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 *  A handler for display log records in a window
 */
public class WindowHandler extends StreamHandler {
    private JFrame frame;
    public WindowHandler(){
        frame = new JFrame();
        final JTextArea output = new JTextArea();
        output.setEditable(false);  //²»ÄÜ±à¼­
        frame.setSize(200, 200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                //not called
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                output.append(new String(b, off, len));
            }
        });
    }

    public void publish(LogRecord record) {
        if(!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}
