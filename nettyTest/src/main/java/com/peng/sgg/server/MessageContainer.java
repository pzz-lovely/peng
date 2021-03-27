package com.peng.sgg.server;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author lovely
 * @Create 2020-09-11 7:49
 * @Description 自己做一个线程安全的添加元素?
 */
public class MessageContainer {
    private LinkedBlockingQueue<Message> messageContainer;
    private volatile Message recently ;

    public MessageContainer() {
        messageContainer = new LinkedBlockingQueue<>(100);
    }

    public void addMessage(String id, String message) throws InterruptedException {
        Message msg = new Message(id, message, new Date());
        messageContainer.offer(msg);
    }

    public Message[] getMessage() throws InterruptedException {
        return messageContainer.toArray(new Message[0]);
    }

    public Message getRecentlyMessage() {
        return recently;
    }


    public void setRecentlyMessage(String id,String message) {
        recently = new Message(id, message, new Date());
        messageContainer.offer(recently);
    }
}