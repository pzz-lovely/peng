package two.chapter1.streams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-02-28 13:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CreatingStreams {


    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.println("firstElements ");
        System.out.print(title + " : ");
        for (int i = 0; i < firstElements.size(); i++) {
            if(i > 0) System.out.print(",");
            if(i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two\\chapter1\\stream.txt");
        String contents = new String(Files.readAllBytes(path),StandardCharsets.UTF_8);
        //产生一个元素 给指定的值
        Stream<String> worlds = Stream.of(contents.split("\r\n"));
        show("worlds", worlds);
        //of(T...values)
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        //产生一个空流
        Stream<String> silence = Stream.empty();
        show("silence", silence);
        //产生一个无限流
        Stream<String> echo = Stream.generate(() -> "Echo");
        show("Echo", echo);
        //产生一个无限流
        Stream<Double> random = Stream.generate(Math::random);
        show("random", random);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\r\n").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines",lines);
        }
    }
}
