package mldn.optional;

import java.util.Optional;

/**
 * @Auther lovely
 * @Create 2020-03-24 20:33
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class OptionalDemo1 {
    public static void main(String[] args) {
        Optional<String> optional = Optional.ofNullable(/*0.0*/null);
        if(optional.isPresent())    //有数据保存
        {
            String value = optional.get();
            System.out.println(value);
        }
    }
}
