package mldn.cascade;

/**
 * @Auther lovely
 * @Create 2020-03-21 22:47
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public interface FieldContentSplit {
    public static final String SPLIT_VALUE = "\\|";

    public static final String FIELD_SPLIT_VALUE = ":";

    public static String[] splitAll(String values) {
        return values.split(SPLIT_VALUE);
    }

    public static String[] splitField(String value) {
        return value.split(FIELD_SPLIT_VALUE,2);
    }
}
