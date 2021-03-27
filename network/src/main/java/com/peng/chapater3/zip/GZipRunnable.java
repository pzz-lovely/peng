package com.peng.chapater3.zip;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * @Auther lovely
 * @Create 2020-04-30 9:09
 * @Description todo
 */
public class GZipRunnable implements Runnable {
    private final File input;

    public GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        //不压缩已经压缩的文件
        if (!input.getName().endsWith(".gz")) {
            File output = new File(input.getParent(), input.getName() + ".gz");
            if (!output.exists()) { //不覆盖已经存在的文件
                try(InputStream in = new BufferedInputStream(new FileInputStream(input));
                        OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)))){
                    int b ;
                    while((b = in.read())!= -1)
                        out.write(b);
                    out.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
