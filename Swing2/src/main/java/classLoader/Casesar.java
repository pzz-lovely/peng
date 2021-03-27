package classLoader;

import java.io.*;

/**
 * @Auther lovely
 * @Create 2020-03-16 15:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Casesar {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("USAGE:java classLoader.Caesar in out key");
            return;
        }
        try (FileInputStream in = new FileInputStream(args[0]); FileOutputStream out = new FileOutputStream(args[1])) {
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch + key);
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
