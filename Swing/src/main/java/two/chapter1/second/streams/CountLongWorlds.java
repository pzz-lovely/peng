package two.chapter1.second.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-03-10 16:43
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CountLongWorlds {
    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two" +
                "\\english.txt")),StandardCharsets.UTF_8);
        List<String> worlds = Arrays.asList(content.split("\r\n"));
        //并行流
        Stream<String> parallelWorlds = worlds.parallelStream();
        //顺序流
        Stream<String> stream = worlds.stream();
        //过滤掉的
        Stream<String> filterStream = stream.filter(w -> w.length() > 12);

        System.out.print("并行流:");
        parallelWorlds.forEach(e -> System.out.print(e));
        System.out.println();

        System.out.print("顺序流:");
        //stream.forEach(e -> System.out.print(e));
        System.out.println();

        System.out.print("过滤的流:");
        filterStream.forEach(e -> System.out.print(e));
        long count = filterStream.count();
        System.out.println("count = "+count);
    }
}
