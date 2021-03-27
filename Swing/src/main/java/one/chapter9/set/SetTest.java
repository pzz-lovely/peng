package one.chapter9.set;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Auther lovely
 * @Create 2020-02-20 9:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description\
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> worlds = new HashSet<>();   //
        long totalTime = 0;

        try (Scanner in = new Scanner(new FileInputStream(new File("D:\\task\\peng\\Swing\\src\\main\\java\\one.chapter9\\set\\english.txt")))) {
            while (in.hasNext()) {
                String world = in.next();
                long callTime = System.currentTimeMillis();
                worlds.add(world);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(worlds.hashCode());

        Iterator<String> ite = worlds.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
        System.out.println("...");
        System.out.println(worlds.size()+" distinct wolds "+totalTime+" milliseconds");
    }
}
