package com.peng.chapater4.weblog;

import java.net.InetAddress;
import java.util.concurrent.Callable;

/**
 * @Auther lovely
 * @Create 2020-04-30 11:37
 * @Description todo
 */
public class LoopupTask implements Callable<String> {
    private String line;

    public LoopupTask(String line) {
        this.line = line;
    }

    @Override
    public String call() throws Exception {
        try {
            //分解IP地址
            int index = line.indexOf(' ');
            String address = line.substring(0, index);
            String theRest = line.substring(index);
            String hostName = InetAddress.getByName(address).getHostName();
            return hostName + " " + theRest;
        } catch (Exception ex) {
            return line;
        }
    }
}
