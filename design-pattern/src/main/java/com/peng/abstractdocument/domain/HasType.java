package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.enums.Property;

import java.util.Optional;

/**
 * @Auther lovely
 * @Create 2021-03-01 16:24
 * @Description todo
 */
public interface HasType extends Document {
    default Optional<String> getType(){
        return Optional.ofNullable((String) get(Property.TYPE.toString()));
    }
}
