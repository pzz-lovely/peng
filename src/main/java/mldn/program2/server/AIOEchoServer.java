package mldn.program2.server;

import mldn.program2.thread.AIOServerThread;

import java.io.IOException;

public class AIOEchoServer {
    //comparable
    public static void main(String[] args) throws IOException {
        new Thread(new AIOServerThread(),"0.0").start();
    }
}
