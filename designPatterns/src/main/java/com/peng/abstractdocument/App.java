package com.peng.abstractdocument;

import com.peng.abstractdocument.domain.Car;
import com.peng.abstractdocument.domain.enums.Property;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author lovely
 * @Create 2021-02-26 16:41
 * @Description
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Constructing parts and car");

        var wheelProperties = Map.of(
                Property.PARTS.toString(), "wheel",
                Property.MODEL.toString(), "15C",
                Property.PRICE.toString(), 100L);


        var doorProperties = Map.of(
                Property.TYPE.toString(), "door",
                Property.MODEL.toString(), "Lambo",
                Property.PARTS.toString(), 300L
        );

        var carProperties = Map.of(
                Property.MODEL.toString(), "3000SL",
                Property.PRICE.toString(), 1000L,
                Property.PARTS.toString(), List.of(wheelProperties, doorProperties)
        );

        var car = new Car(carProperties);


        log.info("Here is our car");
        log.info("-> model: {}", car.getModel().orElseThrow());
        log.info("-> price: {}", car.getPrice().orElseThrow());
        log.info("-> parts: ");
        car.getParts().forEach(p -> log.info("\t{}{}{}", p.getType(), p.getModel(), p.getPrice()));
    }
}