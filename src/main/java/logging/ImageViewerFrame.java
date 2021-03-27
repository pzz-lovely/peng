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
 *  ��ʾͼ���֡
 *
 */
public class





ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    //��ǩ
    private JLabel label;
    private static Logger logger = Logger.getLogger("com.peng.corejava");

    public ImageViewerFrame(){
        logger.entering("ImageViewFrame", "<init>");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        //set up menu bar �˵���
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);  //���� file���� �͵� ��ӵ� �˵���

        JMenuItem menuItem = new JMenuItem("open");
        menu.add(menuItem);
        menuItem.addActionListener(new FileOpenListener()); //ִ�в���

        JMenuItem exitMenu = new JMenuItem("exit");
        menu.add(exitMenu);
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting..");
                System.exit(0);
            }
        });     //�˳�����

        //use a label to display the images
        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }

    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", e);

            //set up file chooser �ļ�ѡ����
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            //accept all files ending with .gif
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {     //����
                    return "GIF Images";
                }
            });

            //show file chooser dialog ��ʾ�ļ�ѡ����
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
