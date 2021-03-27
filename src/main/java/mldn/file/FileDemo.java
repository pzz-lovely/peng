package mldn.file;

import java.io.File;
import java.io.IOException;

/**
 * @Auther lovely
 * @Create 2020-03-29 12:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 普通的判断目录是否存在  多次判断会带来性能损耗 所以就将判断设置在 静态代码块中执行
 */
public class FileDemo {

    private static final String SEPARATOR = File.separator;
    private static String path = "D:" + SEPARATOR + "task\\peng2\\src\\main\\java\\mldn\\file\\test";
    private static File file = new File(path);


    static{
        try{
            if (!file.exists()) {
                if(file.mkdirs())
                    file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                File file = new File(path, Thread.currentThread().getName() + ".txt");
                if (file.exists()) {
                    file.delete();
                }else{
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, "ThreadDemo" + i).start();
        }
    }
}
