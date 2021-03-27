package logging;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Auther lovely
 * @Create 2020-02-10 10:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description The frame that shows the image
 *  显示图像的帧
 *
 */
public class





ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    //标签
    private JLabel label;
    private static Logger logger = Logger.getLogger("com.peng.corejava");

    public ImageViewerFrame(){
        logger.entering("ImageViewFrame", "<init>");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        //set up menu bar 菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);  //将有 file名称 餐单 添加到 菜单栏

        JMenuItem menuItem = new JMenuItem("open");
        menu.add(menuItem);
        menuItem.addActionListener(new FileOpenListener()); //执行操作

        JMenuItem exitMenu = new JMenuItem("exit");
        menu.add(exitMenu);
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting..");
                System.exit(0);
            }
        });     //退出操作

        //use a label to display the images
        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }

    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", e);

            //set up file chooser 文件选择器
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            //accept all files ending with .gif
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {     //描述
                    return "GIF Images";
                }
            });

            //show file chooser dialog 显示文件选择器
            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            //if image file accepted set it as icon of the label
            if (r == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            }
            else logger.fine("File open dialog canceled.");
            logger.exiting("ImageViewerFrame.FileOpenListener","actionPerformed");
        }
    }
}
