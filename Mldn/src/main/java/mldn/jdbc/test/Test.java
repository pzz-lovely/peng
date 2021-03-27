package mldn.jdbc.test;

import mldn.jdbc.entity.User;
import mldn.jdbc.util.CreateDDLUtil;

/**
 * @Auther lovely
 * @Create 2020-03-30 21:35
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        String ddl = CreateDDLUtil.getDDL(User.class);
        System.out.println(ddl);

    }
}
