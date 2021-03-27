package com.peng.abstractdocument;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Author lovely
 * @Create 2021-02-26 17:14
 * @Description
 */
public abstract class AbstractDocument implements Document {

    private Map<String, Object> properties;

    protected AbstractDocument(Map<String, Object> properties) {
        Objects.requireNonNull(properties, "properties map is required");
        this.properties = properties;
    }

    @Override
    public Void put(String key, Object value) {
        properties.put(key, value);
        return null;
    }

    @Override
    public Object get(String key) {
        return properties.get(key);
    }

    @Override
    public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constrictor) {
        return Stream.ofNullable(get(key))
                .filter(Objects::nonNull)
                .map(el -> (List<Map<String, Object>>) el)
                .findAny()
                .stream()
                .flatMap(Collection::stream)
                .map(constrictor);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("{");
        properties.forEach((key, value) -> builder.append("[").append(key).append(" : ").append(value).append("]"));
        builder.append("}");
        return builder.toString();
    }
}