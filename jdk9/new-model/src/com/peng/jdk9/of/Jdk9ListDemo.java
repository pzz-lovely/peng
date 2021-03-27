package com.peng.jdk9.of;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lovely
 * @create 2021-03-11 11:32
 * @description
 */
public class Jdk9ListDemo {
    public static void main(String[] args) {
        List<Integer> list = Collections.unmodifiableList(List.of(1, 2, 3));

        Set<Integer> sets = Set.of(1, 2, 3);

        Map<String, Integer> maps = Map.of(
                "Tom", 23,
                "Jerry", 22,
                "Lilei", 12
        );

        Map<String, Integer> map = Map.ofEntries(Map.entry("Tom", 23), Map.entry("Jerry", 21));
    }
}