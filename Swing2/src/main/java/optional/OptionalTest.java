package optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Auther lovely
 * @Create 2020-03-10 20:23
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class OptionalTest {
    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("D:\\task\\peng2\\Swing2\\src\\main\\resources\\english.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(content.split("\\n"));
        Optional<String> optionalValue = wordList.stream().filter(s -> s.length() > 12).findFirst();
        System.out.println(optionalValue.toString());
        System.out.println(optionalValue.get());
        System.out.println("OptionalValue " + optionalValue.orElse("《You Have Only One Life》"));

        Optional<String> optionalString = Optional.empty();
        System.out.println(optionalString.orElse("N/A"));

    }
}
