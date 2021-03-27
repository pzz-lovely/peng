package com.peng.dubborpc.customer;

import com.peng.dubborpc.netty.client.EchoClient;
import com.peng.dubborpc.publicinterface.HelloService;

/**
 * @Author lovely
 * @Create 2020-09-21 8:37
 * @Description todo
 */
public class ClientBootstrap {
    public static final String providerName = "HelloService#hello#";


    public static void main(String[] args) throws InterruptedException {
        EchoClient customer = new EchoClient();


        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (; ; ) {
            Thread.sleep(2 * 1000);
            //通过代理对象调用服务提供者的方法(服务)
            String res = service.hello("你好 dubbo~");
            System.out.println("调用的结果 res= " + res);
        }
    }
}