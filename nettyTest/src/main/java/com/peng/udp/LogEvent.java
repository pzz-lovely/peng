package com.peng.udp;

import java.net.InetSocketAddress;

/**
 * @Auther lovely
 * @Create 2020-09-01 14:12
 * @Description todo
 */
public class LogEvent {
    public static final byte SEPARATOR = (byte) ':';

    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;


    public LogEvent(String logfile, String msg) {
        this(null, logfile, msg, -1);
    }

    public LogEvent(InetSocketAddress source, String logfile, String msg, long received) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }



    public static byte getSEPARATOR() {
        return SEPARATOR;
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogfile() {
        return logfile;
    }

    public String getMsg() {
        return msg;
    }

    public long getReceived() {
        return received;
    }
}
