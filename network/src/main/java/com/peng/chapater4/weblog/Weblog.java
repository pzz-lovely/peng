package com.peng.chapater4.weblog;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther lovely
 * @Create 2020-04-30 10:51
 * @Description todo
 */
public class Weblog {
    public static void main(String[] args) {
        String filename = "";
        try (FileInputStream fin = new FileInputStream(filename);
            Reader in = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(in)
        ) {
            for (String entry = reader.readLine();
                 "".equals(entry);
                 entry = reader.readLine()) {
                int index = entry.indexOf(' ');
                String ip = entry.substring(0, index);
                String theRest = entry.substring(index);

                //向DNS请求主机名 并显示
                try {
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostAddress() + " " + theRest);
                } catch (UnknownHostException ex) {
                    System.err.println(entry);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
