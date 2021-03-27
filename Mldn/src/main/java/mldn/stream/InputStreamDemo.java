package mldn.stream;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @Auther lovely
 * @Create 2020-03-30 10:26
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class InputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\task\\peng2\\src\\main\\java\\mldn\\stream\\test\\OutputStreamTest.txt");
        InputStream in = new FileInputStream(file);
        byte[] data = new byte[1024];
        in.read(data);
        System.out.println(new String(data, Charset.forName("GBK")).trim());
    }
}
