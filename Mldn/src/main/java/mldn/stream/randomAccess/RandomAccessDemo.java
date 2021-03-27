package mldn.stream.randomAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Auther lovely
 * @Create 2020-03-30 13:26
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RandomAccessDemo {
    private static final int MAX_LENGTH = 8;
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\task\\peng2\\Mldn\\src\\main\\java\\mldn\\stream\\randomAccess\\test\\test.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        //write
        read(raf);
        raf.close();
    }


    private static void read(RandomAccessFile raf) throws IOException {
        raf.skipBytes(12);
        byte data[] = new byte[MAX_LENGTH];
        raf.read(data);
        int age = raf.readInt();
        System.out.printf("Article 2 data name:%s,age:%d\n",new String(data).trim(),age);
        //第一条数据
        raf.seek(0);
        raf.read(data);
        age = raf.readInt();
        System.out.printf("Article 2 data name:%s,age:%d\n",new String(data).trim(),age);

        //读取第五条
        raf.skipBytes(36);
        raf.read(data);
        age = raf.readInt();
        System.out.printf("Article 2 data name:%s,age:%d\n",new String(data).trim(),age);
    }
    private static void write(RandomAccessFile raf) throws IOException {
        String[] names = new String[]{"zhangsan","lisi","wangwu","zhaoliu","sunqi"};
        int[] ages = new int[]{21, 23, 20, 19, 15};
        for (int i = 0; i < names.length; i++) {
            String name = addEscape(names[i]);
            raf.write(name.getBytes());
            raf.writeInt(ages[i]);
        }
    }

    public static String addEscape(String val) {
        StringBuffer buffer = new StringBuffer(val);
        while (buffer.length() < 8) {
            buffer.append(" ");
        }
        return buffer.toString();
    }
}
