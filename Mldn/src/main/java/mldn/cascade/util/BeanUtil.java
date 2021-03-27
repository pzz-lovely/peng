package mldn.cascade.util;

import mldn.cascade.FieldContentSplit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Auther lovely
 * @Create 2020-03-21 22:41
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BeanUtil {
    private BeanUtil(){}

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneId zoneId = ZoneId.systemDefault();
    /**
     * 实现对象反射属性赋值的处理操作
     * @param object 要进行实例化处理的对象实例(不允许为空)
     * @param value 满足于指定格式要求字符串 ("属性名称:值")
     */
    public static void setObjectValue(Object object, String value) throws Exception {
        String[] all = FieldContentSplit.splitAll(value);
        for (String content : all) {
            try {
                //获取属性名称和值
                String fields[] = FieldContentSplit.splitField(content);
                //找到属性
                if (fields[0].contains(".")) {
                    String cascade[] = fields[0].split("\\.");
                    Object fieldObject = instanceCascadeObject(object, cascade);
                    setFieldValue(fieldObject,cascade[cascade.length-1], fields[1]);
                }else { //没有找到点 就为单联操作
                    setFieldValue(object,fields[0], fields[1]);
                }
            } catch (NoSuchFieldException n) {
            }
        }
    }

    /**
     * 实现指定对象实例内容的设置
     * @param object
     * @param fieldName
     */
    private static void setFieldValue(Object object, String fieldName,String fieldValue)throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        //找到属性对应的 setter方法
        Method method = object.getClass().getDeclaredMethod("set" + StringUtil.initCap(fieldName), field.getType());
        method.invoke(object, convert(fieldValue, field));
    }


    private static Object instanceCascadeObject(Object object, String cascade[]) throws Exception {
        //emp.dept.name
        for (int i = 0; i < cascade.length - 1; i++) {
            //由于级联对象可以能重复使用多次，锁必须放置有可能出现的重复对象实例化问题
            Method getMethod = object.getClass().getMethod("get" + StringUtil.initCap(cascade[i]));
            Object instance = getMethod.invoke(object);
            if (instance == null) {
                //需要反射调用当前对象的setter方法进行对象的手工实例化
                Field field = object.getClass().getDeclaredField(cascade[i]);
                //属性的对象
                Object o = field.getType().getDeclaredConstructor().newInstance();
                //调用类的setter方法
                Method setMethod = object.getClass().getMethod("set" + StringUtil.initCap(cascade[i]), field.getType());
                //实例 属性对象。
                setMethod.invoke(object, o);
                object = o; //需要更换级联的下一步操作   替换引用
            }else{
                object = instance;     //替换引用
            }
        }
        //System.out.println(object);
        return object;
    }

    public static Object convert(String value, Field field) {
        try {
            String fieldTypeName = field.getType().getName();
            switch (fieldTypeName) {
                case "java.lang.String":
                    return value;
                case "java.lang.Integer":
                    return Integer.parseInt(value);
                case "int":
                    return Integer.parseInt(value);
                case "java.lang.Double":
                    return Double.parseDouble(value);
                case "java.math.BigDecimal":
                    return new BigDecimal(value);
                case "java.lang.Long":
                    return Long.parseLong(value);
                case "java.util.Date":
                    LocalDateTime localDateTime = LocalDateTime.parse(value, DATE_TIME_FORMATTER);
                    Instant instant = localDateTime.atZone(zoneId).toInstant();
                    return Date.from(instant);
            }
        } catch (Exception e) { e.printStackTrace();}
        return null;
    }

}
