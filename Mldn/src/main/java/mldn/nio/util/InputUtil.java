package mldn.nio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther lovely
 * @Create 2020-04-28 20:54
 * @Description todo
 */
public class InputUtil {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getString(String prompt) throws IOException {
        boolean flag = true;
        String str = null;
        while (flag) {
            System.out.println(prompt);
            str = reader.readLine();
            if (str == null || "".equals(str)) {
                System.out.println("数据输入错误，请重新输入！！！");
            }else{
                flag = false;
            }
        }
        return str;
    }
}
