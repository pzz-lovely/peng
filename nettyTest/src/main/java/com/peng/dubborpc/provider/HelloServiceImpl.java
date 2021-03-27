package com.peng.dubborpc.provider;

import com.peng.dubborpc.publicinterface.HelloService;

/**
 * @Author lovely
 * @Create 2020-09-21 8:16
 * @Description todo
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息=" + msg);


        if(msg !=null)
            return "你好客户端，我已收到你的消息[" + msg + "]";
        else
            return "你好客户端，我已收到你的消息";
    }
}