package two.chapter1.collecting;

import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-02-28 18:18
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("D:\\task\\peng\\Swing\\src\\main\\java\\two" +
                "\\english.txt")), StandardCharsets.UTF_8);
        /*非字母分隔符*/
        List<String> wordList = Arrays.asList(content.split("\\PL"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[in]","{in}"));
    }


    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + " : " + set.getClass().getName());
        System.out.println("["+set.stream().limit(10).map(Object::toString).collect(Collectors.joining(","))+"]");
    }


    public static void main(String[] args) throws IOException {
        Iterator iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
        Object numbers[] = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        //Note it's an Object array
        System.out.println("Object Array : " + Arrays.toString(numbers));

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number : " + number);
            System.out.println("The following statement throw an exception");
            Integer[] numbers2 = (Integer[]) numbers;
        } catch (ClassCastException e) {
            System.out.println(e);
        }

        Integer[] number3 = Stream.iterate(1, n -> n + 1).limit(10).toArray(Integer[]::new);

        System.out.println("Integer arrays : " + number3);

        Set<String> noVowelTest = noVowels().collect(Collectors.toSet());

        show("nowVowelSet", noVowelTest);
        TreeSet<String> noVoweTree = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("nowVowelTreeSet", noVoweTree);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining:" + result);

    }

}
