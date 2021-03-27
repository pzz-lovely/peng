package io.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Auther lovely
 * @Create 2020-03-13 11:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ZipTest {
    public static void main(String[] args) throws IOException {
        /*ZipInputStream zipIn = new ZipInputStream(new FileInputStream("E:\\nvshen\\alal.7z"));
        ZipEntry entry;
        byte[] bytes = new byte[1024];
        zipIn.read(bytes);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
        while ((entry = zipIn.getNextEntry()) != null) {

        }*/
        FileSystem fs = FileSystems.newFileSystem(Paths.get("D:", "task","frame","lib.zip"), null);
        System.out.println(fs.getPath(""));
        Path path = Files.walkFileTree(fs.getPath("\\"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}