package streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *创建流的各种方式
 */
public class CreatingStreams {
    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title+":");
        for (int i = 0; i < firstElements.size(); i++) {
            if(i > 0) System.out.print(",");
            if(i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print(".......");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\C\\Test\\abc.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of("gently", "down", "the", "stream");
        show("words", words);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("random", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);


    }
}
