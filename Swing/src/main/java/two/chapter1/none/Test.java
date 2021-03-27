package two.chapter1.none;

/**
 * @Auther lovely
 * @Create 2020-02-28 16:10
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        //访问静态方法
        IConvert<String, String> convert1 = Something::startWith;
        System.out.println(convert1.convert("123"));


        //访问对象方法
        Something something = new Something();
        IConvert<String, String> convert2 = something::endWith;
        System.out.println(convert2.convert("123"));

        //访问构造方法
        IConvert<String, Something> convert = Something::new;
        System.out.println(convert.convert("constructors"));
/*
        总结：我们可以把类Something中的方法static String startsWith(String s){...}、String endWith(String s){...}、Something(String something){...}看作是接口IConvert的实现，因为它们都符合接口定义的那个“模版”，有传参类型F以及返回值类型T。比如构造方法Something(String something)，它传参为String类型，返回值类型为Something。注解@FunctionalInterface保证了接口有且仅有一个抽象方法，所以JDK能准确地匹配到相应方法。


        总结:我们可以把了Something中的方法static String startWith(String s){}、String endWidth(String s){}、Something(String
        something){} 看作是接口IConvert的实现，因为他们复合接口定义的那个模板，有传参类型F以及返回值类型T。比如构造方法Something(String something),
        它传参为String类型，返回值为Something。注解@FunctionInterface保证了接口有且仅有一个抽象方法
*/

    }
}
