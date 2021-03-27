package two.chapter1.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther lovely
 * @Create 2020-02-28 11:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two" +
                "\\chapter1\\stream.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\r\n"));

        System.out.println(words);
        long count = 0;
        for (String w : words) {
            if(w.length() > 12 )count++;
        }
        System.out.println(count);
        //顺序 流
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
        //并行流
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}
