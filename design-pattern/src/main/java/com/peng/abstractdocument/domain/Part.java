package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @author lovely
 * @create 2021-03-01 16:46
 * @description
 */
public class Part extends AbstractDocument implements HasModel, HasType, HasPrice {


    public Part(Map<String, Object> properties) {
        super(properties);
    }
}