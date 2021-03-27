package mldn.nio.echo2;

import java.io.*;

public class InputUtil {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static String getString(String prompt) throws IOException {
        String str = "";
        boolean flag = true;
        while (flag) {
            System.out.println(prompt);
            str = br.readLine();
            if (str == null || "".equals(str)) {
                System.out.println("输入的数据不正确");
                flag= false;
            }
        }
        return str;
    }

}
