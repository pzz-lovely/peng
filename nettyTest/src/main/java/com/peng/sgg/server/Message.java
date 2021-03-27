package com.peng.sgg.server;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author lovely
 * @Create 2020-09-11 7:50
 * @Description todo
 */
public class Message {
    private String id;
    private String message;
    private Date date;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Message(String id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return message;
    }

    public void setName(String name) {
        this.message = name;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "客户端" + id + "发送消息:" + message + "日期:" + getDate();
    }
}