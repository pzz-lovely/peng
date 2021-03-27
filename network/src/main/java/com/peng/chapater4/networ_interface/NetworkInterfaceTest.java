package com.peng.chapater4.networ_interface;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @Auther lovely
 * @Create 2020-04-30 10:32
 * @Description todo
 */
public class NetworkInterfaceTest {
    public static void main(String[] args) {
        try{
            getByName();
            getByInetAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getByInetAddress() throws UnknownHostException, SocketException {
        InetAddress local = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(local);
        if (ni == null) {
            System.err.println("That's weird. No local loopback address");
        }
        System.out.println(" LocalHost:"+local+" = "+ni);
    }

    private static void getByName() throws SocketException {
        /*NetworkInterface ni = NetworkInterface.getByName("CE31");
            if (ni == null) {
                System.err.println("No such interface:CE31");
            }*/
        NetworkInterface ni = NetworkInterface.getByName("ELX100");
        if (ni == null) {
            System.err.println("No such interface:ELX100");
        }
    }


}
