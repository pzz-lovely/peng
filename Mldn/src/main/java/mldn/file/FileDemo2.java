package mldn.file;

import java.io.File;

/**
 * @Auther lovely
 * @Create 2020-03-30 8:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FileDemo2 {
    public static void main(String[] args) {
        File file ;
    }
    public static void info(File file) {
        if (file.isDirectory()) {
            File list[] = file.listFiles((f) -> f.isDirectory() ? true : f.getName().endsWith(".txt"));
            if (file != null) {
                for(File temp : list){
                    info(file);
                }
            }
        }else{
            System.out.println(file);
        }
    }
}
