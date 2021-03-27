package com.peng.abstractdocument.domain;

import com.peng.abstractdocument.Document;
import com.peng.abstractdocument.domain.enums.Property;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2021-02-26 17:20
 * @Description todo
 */
public interface HasParts extends Document {
    default Stream<Part> getParts(){
        return children(Property.PARTS.toString(), Part::new);
    }

}
