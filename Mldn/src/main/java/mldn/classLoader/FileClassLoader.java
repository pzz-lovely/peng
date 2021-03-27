package mldn.classLoader;

import java.io.*;

/**
 * @Auther lovely
 * @Create 2020-04-09 13:13
 * @Description todo
 */
public class FileClassLoader extends ClassLoader {
    // 获取class文件路径
    private static final String CLASS_PATH = "F:" + File.separator + "Message.class";


    public Class<?> getData(String className) throws Exception {
        byte[] bytes = fileReader();
        if(bytes != null)
            return super.defineClass(className, bytes, 0, bytes.length);
        return null;
    }

    private static byte[] fileReader() throws Exception {
        InputStream in = new FileInputStream(new File(CLASS_PATH));
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int len = 0;
        while ((len = in.read()) != -1) {
            bao.write(len);
        }
        byte[] bytes = bao.toByteArray();
        in.close();
        bao.close();
        return bytes;
    }


}
