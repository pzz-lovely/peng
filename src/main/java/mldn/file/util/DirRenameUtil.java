package mldn.file.util;

import java.io.File;

/**
 * @Auther lovely
 * @Create 2020-03-30 8:55
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class DirRenameUtil {
    private File dir;
    private int sequenceLength; //保存整体文件的编号的最大长度
    private int fileCount;
    public DirRenameUtil(File dir) {
        this.dir = dir;
        this.calcFileCount(dir);
        this.sequenceLength = String.valueOf(this.fileCount).length();
    }

    private void calcFileCount(File file) {
        if (file.isDirectory()) {
            File list[] = file.listFiles();
            if (list != null) {
                for (File sub :list)
                    this.calcFileCount(sub);
            }
        }else{
            if(file.isFile()){  //当前路径是文件
                this.fileCount++;
            }
        }
    }

    public void rename(){
        this.renameHandle(this.dir);
    }

    private void renameHandle(File file) {
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            if (list != null) {
                for (File sub : list) {
                    this.renameHandle(sub);
                }
            }
        }
    }
}
