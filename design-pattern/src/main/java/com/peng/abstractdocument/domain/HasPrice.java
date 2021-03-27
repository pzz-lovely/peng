package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.enums.Property;

import java.util.Optional;

/**
 * @author lovely
 * @create 2021-03-01 16:41
 * @description
 */
public interface HasPrice extends Document {
    default Optional<Number>  getPrice(){
        return Optional.ofNullable((Number) get(Property.PRICE.toString()));
    }
}