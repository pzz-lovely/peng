package com.peng.abstractdocument;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2021-02-26 17:10
 * @Description Documnt interface
 */
public interface Document {

    /**
     * Puts the value related to the key
     * @param key element key
     * @param value element value
     * @return Void
     */
    Void put(String key, Object value);

    /**
     * Gets the value for the key
     * @param key element key
     * @return value of null
     */
    Object get(String key);


    /**
     * Gets the stream of child documents
     * @param key element key
     * @param constrictor constructor of child class
     * @return
     */
    <T> Stream<T> children(String key, Function<Map<String, Object>, T> constrictor);
}
