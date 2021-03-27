package two.chapter1.second.streams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-03-10 17:13
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CreatingStreams {
    static int index = 0;
    public static void main(String[] args) throws IOException {
        String worlds = new String(Files.readAllBytes(Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two" +
                "\\english.txt")), StandardCharsets.UTF_8);
        //转换成流状态
        show("stream.of() : ", Stream.of("0.0","0.1","0.2","0.3","0.5"));
        //产生一个空的流
        show("empty() : ", Stream.empty());
        //产生一个无限流 反复调用 function函数 而建成的
        show("generate : ", Stream.generate(() -> {
            return index++ + "";
        }));
        //产生一个无限流 跟迭代一样的
        show("iterate : ",Stream.iterate(BigInteger.ONE,n -> n.add(BigInteger.ONE)));

        System.out.println(worlds.split("\\n").length);

        //产生一个流 它的元素是根据 数组中的元素
        show("stream(T[]) : ", Arrays.stream(worlds.split("\\n")));
        //产生 一个流 根据 Pattern.splitAsStream(CharSequence input)
        show(" Pattern.splitAsStream : " , Pattern.compile("\\n").splitAsStream(worlds));


    }


    //展示stream的展示
    public static <T> void show(String title, Stream<T> stream) {
        final int size = 5;
        //从流 中返回 5行元素成为 集合
        List<T> firstElements = stream.limit(size+1).collect(Collectors.toList());
        System.out.print(title + " : ");
        /*firstElements.forEach((e) -> {
            System.out.print(e);
        });*/
        for (int i = 0; i < size; i++) {
            if (firstElements.isEmpty()) {
                return;
            }
            System.out.print(firstElements.get(i)+"  ");
        }
        System.out.println();
    }
}
