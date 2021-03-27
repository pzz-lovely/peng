package collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther lovely
 * @Create 2020-03-25 9:17
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示Map的基本用法
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("0.0", 12);
        map.put("0.1", 13);

    }

}
