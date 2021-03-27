package com.peng.abstractdocument;

import com.peng.abstractdocument.domain.Car;
import com.peng.abstractdocument.enums.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author lovely
 * @create 2021-03-01 16:14
 * @description 抽象文档模式 能处理其它非静态属性。此模式使用特征的概念来实现类型安全，并将不用类的属性分离为一组接口。
 *  在 Abstract Document模式中，（ AbstractDocument ）完全实现了Document 接口。然后定义特征，以便能够以通常的静态方式访问属性。
 */
public class AbstractDocumentApp {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDocumentApp.class);

    public static void main(String[] args) {
        LOG.info("Constructing parts and Car");


        var wheelProperties = Map.of(
                Property.TYPE.toString(), "wheel",
                Property.PRICE.toString(),100L,
                Property.MODEL.toString(), "15C"
        );

        var doorProperties = Map.of(
                Property.TYPE.toString(), "door",
Property.MODEL.toString(),"lambo",
                Property.PRICE.toString(),300L
        );

        var carProperties = Map.of(
                Property.MODEL.toString(), "300SL",
                Property.PRICE.toString(), 10000L,
                Property.PARTS.toString(), List.of(wheelProperties, doorProperties)
        );

        var car = new Car(carProperties);


        LOG.info("Here is our car:");
        LOG.info("-> model: {}", car.getModel().orElseThrow());
        LOG.info("-> price: {}", car.getPrice().orElseThrow());
        LOG.info("-> parts: ");
        car.getParts().forEach(p -> LOG.info("\t{}/{}/{}",
                p.getType().orElse(null),
                p.getModel().orElse(null),
                p.getPrice().orElse(null))
        );
    }
}