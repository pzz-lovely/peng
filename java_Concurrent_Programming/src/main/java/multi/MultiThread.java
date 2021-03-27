package multi;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * JXM查看一个普通的的java程包含哪些线程
 */
public class MultiThread {
    public static void main(String[] args) {
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronized信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
            /*
            程序输出:
                [6]Monitor Ctrl-Break 监控Ctrl-Break
                [5]Attach Listener 附加监听器
                [4]Signal Dispatcher 分发处理发送给JVM信号的线程
                [3]Finalizer 调用对象finalize方法的线程
                [2]Reference Handler 清除Reference的线程
                [1]main main线程，用户程序的入口
             */
        }
    }
}
