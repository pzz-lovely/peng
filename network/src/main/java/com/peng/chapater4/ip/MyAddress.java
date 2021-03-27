package com.peng.chapater4.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther lovely
 * @Create 2020-04-30 9:50
 * @Description todo
 */
public class MyAddress {
    public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getLocalHost();
            String hostName = address.getHostName();
            String hostAddress = address.getHostAddress();
            System.out.println("hostName = "+hostName+" hostAddress="+hostAddress);
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
