package com.peng;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther lovely
 * @Create 2020-07-09 9:30
 * @Description todo
 */
public class RegexSql {
    private static final String  path = "D:\\task\\peng2\\src\\main\\java\\com\\peng";
    public static void main(String[] args) throws IOException {
        /*2017-03-25 16:57:05*/
        Pattern pattern = Pattern.compile("'(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+)'");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path+"\\mmall.sql"))));
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path+"\\mmall-copy.sql"))));
        String content = "";
        while ((content = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(content);
            int index = 0;
            while (matcher.find()) {
                content = content.replace(matcher.group(), "default");
                System.out.println(matcher.group());
                // index++;
            }
//            content.replaceAll(pattern.pattern(), "default");
            writer.write(content+"\n");
        }
        writer.close();
        reader.close();
    }
}
