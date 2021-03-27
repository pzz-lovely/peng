package io.memoryMap;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * @Auther lovely
 * @Create 2020-03-14 14:31
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MemoryMapTest {
    public static long checksumInputStream(Path filename) {
        try (InputStream in = Files.newInputStream(filename)) {
            CRC32 crc = new CRC32();
            int c;
            while((c = in.read()) != -1)
                crc.update(c);
            return crc.getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static long checksumBufferedInputStream(Path filename) {
        try(InputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
            CRC32 crc = new CRC32();
            int c ;
            while((c = in.read()) != -1)
                crc.update(c);
            return crc.getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static long checksumRandomAccessFile(Path filename) {
        CRC32 crc = new CRC32();
        try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
            long length = file.length();
            for (long i = 0; i < length; i++) {
                file.seek(i);
                int c = file.readByte();
                crc.update(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crc.getValue();
    }

    public static long checksumMappedFile(Path filename) {
        CRC32 crc = new CRC32();
        try (FileChannel channel = FileChannel.open(filename)) {
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

            for (int i = 0; i < length; i++) {
                int c = buffer.get();
                crc.update(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crc.getValue();
    }

    public static void main(String[] args) {
        System.out.println("Input Stream");
        long start = System.currentTimeMillis();
        Path filename = Paths.get("E:\\music\\jay\\Try\\派伟俊&周杰伦-Try.flac");
        long crcValue = 0 /*checksumInputStream(filename)*/;
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds ");

        System.out.println();

        System.out.println("Buffered Input Stream");
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds ");

        System.out.println();

        System.out.println("Random Access File");
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds ");

        System.out.println();

        System.out.println("Mapped File");
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds ");
    }
}
