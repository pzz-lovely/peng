package com.peng.jdk8.repeatannotation;

import com.sun.scenario.effect.Filterable;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.lang.annotation.*;

/**
 * @Auther lovely
 * @Create 2021-03-10 17:15
 * @Description todo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable
public @interface Filter {
    String value();
}