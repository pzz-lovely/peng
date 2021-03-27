package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.enums.Property;

import java.util.stream.Stream;

/**
 * @author lovely
 * @create 2021-03-01 16:43
 * @description 零件
 */
public interface HasParts extends Document {
    default Stream<Part> getParts(){
        return children(Property.PARTS.toString(), Part::new);
    }
}