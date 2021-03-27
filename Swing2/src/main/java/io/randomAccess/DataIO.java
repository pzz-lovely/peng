package io.randomAccess;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Auther lovely
 * @Create 2020-03-13 10:15
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class DataIO {
    public static void writeFixedString(String s, int size, DataOutput output) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if(i < s.length()) ch = s.charAt(i);
            output.writeChar(ch);
        }
    }

    public static String readFixedString(int size, DataInput input) throws IOException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char ch = input.readChar();
            i++;
            if(ch == 0) more = false;
            else sb.append(ch);
        }
        input.skipBytes(2 * (size - i));
        return sb.toString();
    }
}
