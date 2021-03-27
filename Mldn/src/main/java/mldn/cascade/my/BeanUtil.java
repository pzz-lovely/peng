package mldn.cascade.my;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-03-22 12:31
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
/*name:0.0|job:aa|age:12|birthday:2019-10-12 12:02:34|
dept.deptNo:20|dept.deptName:教学|
dept.company.cno:1|dept.company.cname:嗷嗷*/
//创建对象 并且给对象赋值
public class BeanUtil{
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneId zoneId = ZoneId.systemDefault();

    public static void setObjectValue(Object obj,String value){
        String content[] = FieldSplit.splitALL(value);
        for(int i = 0 ; i < content.length ;i++){
            try {
                //获取field 的 属性名[0] 属性值[1];
                String[] fieldNameAndValue = FieldSplit.splitField(content[i]);
                if (fieldNameAndValue[0].contains(".")) {
                    String[] fieldObjName = FieldSplit.splitFieldObj(fieldNameAndValue[0]);
                    Object fieldObj = setFieldObject(obj, fieldObjName);
                    setFieldValue(fieldObj, fieldObjName[fieldObjName.length - 1], fieldNameAndValue[1]);
                } else {
                    setFieldValue(obj, fieldNameAndValue[0], fieldNameAndValue[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //为级联对象中的属性 赋值 返回的还是这个obj对象
    public static Object setFieldObject(Object obj,String[] fieldObjName) throws Exception {
        //dept.company.cno
        for(int i = 0 ; i < fieldObjName.length-1;i++){
            Method method = obj.getClass().getMethod("get"+StringUtil.initCap(fieldObjName[i]));
            Object fieldObj= method.invoke(obj);
            //检测多级联 中的 属性对象是否 为空
            if(fieldObj==null){
                //获取 属性
                Field field = obj.getClass().getDeclaredField(fieldObjName[i]);
                fieldObj = field.getType().getConstructor().newInstance();
                //为当前 这个级联对象 的属性赋值
                Method setMethod =  obj.getClass().getDeclaredMethod("set"+StringUtil.initCap(fieldObjName[i]),
                        field.getType());
                setMethod.invoke(obj,fieldObj);	//将下一级联对象 引用
                obj = fieldObj;  //替换引用
            }else{
                obj = fieldObj;	//可能下一级联的对象中，也有下一个级联对象
            }
        }
        return obj;
    }


    //为对象的属性赋值
    public static void setFieldValue(Object obj,String fieldName,String fieldValue)throws Exception{
        Field field = obj.getClass().getDeclaredField(fieldName);
        Method method = obj.getClass().getMethod("set"+StringUtil.initCap(fieldName),field.getType());
        method.invoke(obj,convert(field.getType().getName(),fieldValue));
    }
    //类型转换
    private static Object convert(String typeName,String value){
        try{
            switch(typeName){
                case "java.lang.String":
                    return value;
                case "java.lang.Integer":
                    return Integer.parseInt(value);
                case "java.lang.Double":
                    return Double.parseDouble(value);
                case "java.lang.Long":
                    return Long.parseLong(value);
                case "java.math.BigDecimal":
                    return new BigDecimal(value);
                case "java.util.Date":
                    LocalDateTime localDateTime = LocalDateTime.parse(value,dateTimeFormatter);
                    Instant instant = localDateTime.atZone(zoneId).toInstant();
                    return Date.from(instant);
            }
        }catch(Exception e){}	//可能会出现强转异常
        return null;
    }

}
