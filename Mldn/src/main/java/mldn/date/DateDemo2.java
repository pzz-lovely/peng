package mldn.date;

import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-03-20 21:13
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class DateDemo2 {
    public static void main(String[] args) {
        Date dateA = new Date(System.currentTimeMillis() - 1000);
        Date dateB = new Date();
        System.out.println("两者之差 : " + (dateB.getTime() - dateA.getTime()));
        System.out.println("dateA是否在dateB之前"+dateA.before(dateB));
        System.out.println("dateA是否在dateB之后" + dateA.after(dateB));
    }
}
