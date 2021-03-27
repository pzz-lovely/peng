package mldn.stream;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Auther lovely
 * @Create 2020-03-30 9:33
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class OutputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\task\\peng2\\src\\main\\java\\mldn\\stream\\test\\OutputStreamTest.txt");
        if (!file.getParentFile().exists()) {
            file.mkdirs();
            file.createNewFile();
        }
        String message = "Java并发实战怎么样，AEb85怎样,不行";
        System.out.println(message);
        OutputStream out = new FileOutputStream(file,true);
        out.write(message.getBytes());
        out.close();
    }
}
