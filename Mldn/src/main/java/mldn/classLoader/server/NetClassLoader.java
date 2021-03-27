package mldn.classLoader.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Auther lovely
 * @Create 2020-04-09 13:13
 * @Description todo
 */
public class NetClassLoader extends ClassLoader {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9999;



    public Class<?> getData(String className) throws Exception {
        byte[] bytes = fileReader();
        if (bytes != null)
            return super.defineClass(className, bytes, 0, bytes.length);
        return null;
    }

    private static byte[] fileReader() throws Exception {
        Socket client = new Socket(SERVER_HOST, SERVER_PORT);
        InputStream in = client.getInputStream();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int len = 0;
        while ((len = in.read()) != -1) {
            bao.write(len);
        }
        byte[] bytes = bao.toByteArray();
        client.isInputShutdown();
        in.close();
        bao.close();
        return bytes;
    }


}
