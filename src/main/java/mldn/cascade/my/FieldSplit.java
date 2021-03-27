package mldn.cascade.my;

/**
 * @Auther lovely
 * @Create 2020-03-22 12:29
 * @PACKAGE_NAME
 * @Description
 */
//分隔
public interface FieldSplit{
    public static final String SPLIT_ALL = "\\|";
    public static final String SPLIT_FIELD = ":";
    public static final String SPLIT_FIELD_OBJ = "\\.";

    public static String[] splitALL(String value){
        return value.split(SPLIT_ALL);
    }
    public static String[] splitField(String value){
        return value.split(SPLIT_FIELD,2);
    }
    public static String[] splitFieldObj(String value){
        return value.split(SPLIT_FIELD_OBJ);
    }
}
