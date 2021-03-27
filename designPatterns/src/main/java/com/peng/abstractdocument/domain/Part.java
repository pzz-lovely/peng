package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @Author lovely
 * @Create 2021-02-26 17:33
 * @Description Part Entity
 */
public class Part extends AbstractDocument implements HasType, HasModel, HasPrice {
    public Part(Map<String, Object> properties) {
        super(properties);
    }
}