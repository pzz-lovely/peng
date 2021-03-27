package mldn.runtime;

import java.io.IOException;

/**
 * @Auther lovely
 * @Create 2020-03-22 20:51
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RuntimeDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime(); //获取runtime
        //calc.exe 计算器命令
        //notepad.exe 记事本
        Process process = runtime.exec("notepad.exe");//执行windows系统中的命令
        Thread.sleep(2000);
        process.destroy();  //销毁进程
    }
}
