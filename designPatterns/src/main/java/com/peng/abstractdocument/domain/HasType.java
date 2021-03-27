package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @Author lovely
 * @Create 2021-02-26 17:25
 * @Description
 */
public interface HasType extends Document {
    default Optional<String> getType() {
        return Optional.ofNullable((String) get(Property.TYPE.toString()));
    }
}