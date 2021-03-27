package classLoader;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther lovely
 * @Create 2020-03-16 15:24
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ClassLoaderFrame();
            frame.setTitle("ClassLoaderDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This frame contains two text fields for the name of the class to load and the decryption key
 */
class ClassLoaderFrame extends JFrame{
    private JTextField keyField = new JTextField("3", 4);
    private JTextField nameField = new JTextField("Calculator", 30);

    public ClassLoaderFrame(){
        setSize(300, 200);
        setLayout(new GridBagLayout());

        add(new JLabel("Class"));
        add(nameField);
        add(new JLabel("Key"));
        add(keyField);

        JButton button = new JButton("Load");
        add(button);
        button.addActionListener(event -> runClass(nameField.getText(), keyField.getText()));
        pack();
    }

    private void runClass(String name, String key) {
        try {
            ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


/**
 * this class loader loads encrypted class files
 * */
class CryptoClassLoader extends ClassLoader{
    private int key;

    public CryptoClassLoader(int key) {
        this.key = key;
    }

    /**
     * loads adn decrypt the class file bytes
     * @param name
     * @return
     * @throws IOException
     */
    private byte[] loadClassBytes(String name) throws IOException {
        String cname = name.replace(".", "/") + ".caesar";
        byte[] bytes = Files.readAllBytes(Paths.get(cname));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - key);
        }
        return bytes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte[] classBytes = null;
            classBytes = loadClassBytes(name);
            Class<?> cl = super.defineClass(name, classBytes, 0, classBytes.length);
            if(cl == null) throw new ClassNotFoundException();
            return cl;
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
