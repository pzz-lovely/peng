package com.peng.jdk8.repeatannotation;

/**
 * @author lovely
 * @create 2021-03-10 17:19
 * @description
 */
public class RepeatAnnotationDemo {
    public static void main(String[] args) {
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }
}