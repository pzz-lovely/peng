package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @Auther lovely
 * @Create 2021-02-26 17:16
 * @Description HasModel trait for static access to 'model' propert.
 */
public interface HasModel extends Document {
    default Optional<String> getModel() {
        return Optional.ofNullable((String) get(Property.MODEL.toString()));
    }
}
