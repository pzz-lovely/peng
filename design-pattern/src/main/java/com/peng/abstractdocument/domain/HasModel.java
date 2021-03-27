package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.enums.Property;

import java.nio.file.OpenOption;
import java.util.Optional;

/**
 * @author lovely
 * @create 2021-03-01 16:42
 * @description 模型
 */
public interface HasModel extends Document {
    default Optional<String> getModel(){
        return Optional.ofNullable((String) get(Property.MODEL.toString()));
    }
}