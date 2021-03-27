package mldn.stream.memory;

import java.io.*;

/**
 * @Auther lovely
 * @Create 2020-03-30 11:29
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MemoryDemo {
    public static void main(String[] args) throws IOException {
        /*for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    String sum =String.valueOf(i*i*i + j*j*j + k*k*k);
                    if((""+i+j+k).equals(sum)){
                        System.out.println(i+"^3 + "+j+"^3 + "+k+"^3 = "+sum);
                    }
                }
            }
        }*/

        String message = "Hello lovely";
        InputStream input = new ByteArrayInputStream(message.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        int data = 0;
        while ((data = input.read()) != -1) {
            out.write(Character.toLowerCase(data));
        }
        System.out.println(out);
        out.close();
        input.close();
    }
}
