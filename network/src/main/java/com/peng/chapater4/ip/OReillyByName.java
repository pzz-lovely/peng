package com.peng.chapater4.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-04-30 9:43
 * @Description todo
 */
public class OReillyByName {
    public static void main(String[] args) {
        try {
            /*InetAddress address = InetAddress.getByName("www.baidu.com");*/
            InetAddress address = InetAddress.getByName("183.232.231.172");

            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
            System.out.println(address);
            System.out.println(Arrays.toString(addresses));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
