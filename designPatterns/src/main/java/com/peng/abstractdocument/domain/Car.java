package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @Author lovely
 * @Create 2021-02-26 17:52
 * @Description
 */
public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {

    public Car(Map<String, Object> properties) {
        super(properties);
    }
}