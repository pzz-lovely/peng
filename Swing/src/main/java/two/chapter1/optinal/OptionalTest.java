package two.chapter1.optinal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Auther lovely
 * @Create 2020-02-28 15:12
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class OptionalTest {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two" +
                "\\chapter1\\optinal\\Optional.txt")), StandardCharsets.UTF_8);
        List<String> worlds = Arrays.asList(contents.split("\r\n"));

        Optional<String> optionalValue = worlds.stream().filter(s -> s.contains("Optional")).findFirst();
        //产生这个Optional的值
        System.out.println(optionalValue.orElse("No word") + "contains fred ");
        //产生一个空的
        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        System.out.println("Optional.empty.orElse,result = " + result);
        //返回name
        result = optionalString.orElseGet(()->
            Locale.getDefault().getDisplayName()
        );
        System.out.println("Locale.getDefault.getDisplayName result = " + result);

        try{
            result = optionalString.orElseThrow(IllegalAccessException::new);
            System.out.println("orElseThrow result " + result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        optionalValue = worlds.stream().filter(s -> s.contains("orElse")).findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + "contains orElse"));

        Set<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(" HashSet中的boolean add()作为 实现 = " + added);

        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));

        Optional<Double> result2 = Optional.of(-4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println(" 0.0 "+result2);
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
