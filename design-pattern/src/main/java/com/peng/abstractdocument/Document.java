package com.peng.abstractdocument;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2021-03-01 16:26
 * @Description todo
 */
public interface Document {
    Object get(String key);

    Void put(String key, Object value);

    <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor);

}
