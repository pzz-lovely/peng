package com.peng.jdk8.checktype;

/**
 * @author lovely
 * @create 2021-03-10 17:23
 * @description
 */
public class CheckTypeDemo {
    public static void main(String[] args) {
        final Value< String > value = new Value<>();
        value.getOrDefault( "22", Value.defaultValue() );
    }
}

class Value< T > {
    public static <T> T defaultValue() {
        return null;
    }

    public T getOrDefault(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }
}