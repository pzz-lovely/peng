package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @author lovely
 * @create 2021-03-01 16:49
 * @description
 */
public class Car extends AbstractDocument implements HasPrice, HasModel, HasParts {

    public Car(Map<String, Object> properties) {
        super(properties);
    }
}