package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @Author lovely
 * @Create 2021-02-26 17:23
 * @Description
 */
public interface HasPrice  extends Document {
    default Optional<Number> getPrice() {
        return Optional.ofNullable((Number)get(Property.PRICE.toString()));
    }
}