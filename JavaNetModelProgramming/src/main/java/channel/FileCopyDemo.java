package channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-04-02 9:26
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FileCopyDemo {
    private static void fileExists(File target) throws IOException {
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
            target.createNewFile();
        }
    }

    public static void main(String[] args) {
        FileCopyRunner noBufferStreamCopy = (source,target) ->{
            fileExists(target);
            InputStream fin = new FileInputStream(source);
            OutputStream fOut = new FileOutputStream(target);
            byte[] buffer = new byte[512];
            int result = 0;
            while ((result = fin.read(buffer)) != -1) {
                fOut.write(buffer,0,result);
            }
            fOut.flush();
            fin.close();
            fOut.close();
        };


        FileCopyRunner randomAccessCopy = (((source, target) -> {
            fileExists(target);
            RandomAccessFile randomAccessIn = new RandomAccessFile(source, "rw");
            RandomAccessFile randomAccessOut = new RandomAccessFile(target, "rw");
            int result = 0;
            byte[] buffer = new byte[512];
            while ((result = randomAccessIn.read(buffer)) != -1) {
                randomAccessOut.write(buffer, 0, result);
            }
            randomAccessIn.close();
            randomAccessOut.close();
        }));

        FileCopyRunner bufferedStreamCopy = ((source, target) -> {
            fileExists(target);
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));
            byte[] buffer = new byte[512];
            int result  = 0;
            while ((result = reader.read(buffer)) != -1) {
                out.write(buffer,0,result);
            }
            reader.close();
            out.flush();
            out.close();
        });

        FileCopyRunner nioBufferCopy = ((source, target) -> {
            fileExists(target);
            FileChannel read = new FileInputStream(source).getChannel();
            FileChannel out = new FileOutputStream(target).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(512);
            while (read.read(buffer) != -1) {
                buffer.flip();//转换为读模式
                //只要Buffer中还有数据 就会一直读取
                while (buffer.hasRemaining()) {
                    out.write(buffer);
                }
                buffer.clear();
            }
            read.close();
            out.close();
        });

        FileCopyRunner nioTransferCopy = ((source, target) -> {
            fileExists(target);
            FileChannel read = new FileInputStream(source).getChannel();
            FileChannel out = new FileOutputStream(target).getChannel();
            long transfer = 0L;
            while (transfer != read.size()) {
                 transfer += read.transferTo(0, read.size(), out);
            }
            read.close();
            out.close();
        });

        File source = new File("E:\\music\\AAA.mp4");
        File streamTarget = new File("E:\\music\\stream\\AAA.mp4");
        File bufferStreamTarget = new File("E:\\music\\bufferStream\\AAA.mp4");
        File randomTarget = new File("E:\\music\\random\\AAA.mp4");
        File bufferTarget = new File("E:\\music\\buffer\\AAA.mp4");
        File channel1Target = new File("E:\\music\\channel1\\AAA.mp4");
        File channel2Target = new File("E:\\music\\channel2\\AAA.mp4");
        ExecutorService service = Executors.newFixedThreadPool(10);
        copy(noBufferStreamCopy, source, streamTarget);
        copy(randomAccessCopy, source, randomTarget);
        copy(bufferedStreamCopy, source, bufferTarget);
        copy(nioBufferCopy, source, channel1Target);
        copy(nioTransferCopy, source, channel2Target);

    }


    private static void copy(FileCopyRunner runner,File source,File target) {
        try{
            Instant start = Instant.now();
            runner.copyFile(source,target);
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            System.out.println(target+"消耗秒数:" + duration.getSeconds() + "，毫秒数：" + duration.toMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
@FunctionalInterface
interface FileCopyRunner{
    void copyFile(File source, File target) throws IOException;
}