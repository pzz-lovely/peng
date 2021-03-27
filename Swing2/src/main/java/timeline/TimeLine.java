package timeline;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Auther lovely
 * @Create 2020-03-15 19:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TimeLine {
    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgorithm();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        long millis = duration.toMillis();
        System.out.printf("%d milliseconds\n", millis);

        Instant start2 = Instant.now();
        runAlgorithm2();
        Instant end2 = Instant.now();
        Duration duration1 = Duration.between(start2, end2);
        System.out.printf("%d milliseconds \n", duration1.toMillis());
        boolean overTenTimesFaster = duration.multipliedBy(10).minus(duration1).isNegative();
        System.out.printf("The first algorithm is %s more than ten times faster",overTenTimesFaster ? "":"not");
    }

    public static void runAlgorithm(){

        int size = 10;
        List<Integer> list = new Random().ints().map(i -> i % 100).limit(size).boxed().collect(Collectors.toList());
        Collections.sort(list);
        System.out.println(list);
    }

    public static void runAlgorithm2(){
        int size  = 10;
        List<Integer> list = new Random().ints().map(i -> i % 100).limit(size).boxed().collect(Collectors.toList());
        while (!IntStream.range(1, list.size()).allMatch(i -> list.get(i - 1).compareTo(list.get(i)) <= 0)) {
            Collections.shuffle(list);
        }
        System.out.println(list);
    }
}
