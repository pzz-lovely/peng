package mldn.cascade;

import mldn.cascade.my.BeanUtil;

/**
 * @Auther lovely
 * @Create 2020-03-21 22:29
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ObjectInstanceFactory {
    /**
     * 实现一个简单java类创建处理操作模式
     * @param clazz 要进行操作的简单java类的class对象
     * @param value 包含有属性内容的字符串"属性名称:属性值"
     * @param <T> 由于可以返回 任意的java类对象
     * @return
     */
    public static <T> T create(Class<T> clazz, String value)  {
        try {
            /*
            * Object obj = new Employee(); 当前是这个Employee引用
            * 但是
            * obj = new Dept();  变成了Dept的引用了。
            * */
            Object obj = clazz.getDeclaredConstructor().newInstance();
            BeanUtil.setObjectValue(obj, value);
            return (T) obj;
        } catch (Exception e) {
            return null;
        }
    }
}
