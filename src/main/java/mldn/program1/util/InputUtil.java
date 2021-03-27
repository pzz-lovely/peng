package mldn.program1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUtil {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
    private InputUtil(){}

    public static String getString(String prompt) throws IOException {
        boolean flag = true;
        String str = null;
        while (flag) {
            System.out.println(prompt);
            str = KEYBOARD_INPUT.readLine();
            if (str == null || "".equals(str)) {
                System.out.println("请重新输入:");
            }else
                flag =false;
        }
        return str;
    }

}
