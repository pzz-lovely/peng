package two.chapter1.second.streams;

import two.chapter1.second.Book;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-03-10 18:03
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class StreamMap {
    public static void main(String[] args) throws IOException {
        /*String worlds = new String(Files.readAllBytes(Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two" +
                "\\english.txt")), StandardCharsets.UTF_8);*/
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(new Book("0.0", "世界"), new Book("0.1", "白金")));
        //map
        //Stream<String> map = books.stream().map(e -> e.getName());

        //flatMap
        Stream<String> map = books.stream().flatMap(e -> Stream.of(e.getName()));

        map.forEach(e -> {
            System.out.println(e);
        });
    }
}
