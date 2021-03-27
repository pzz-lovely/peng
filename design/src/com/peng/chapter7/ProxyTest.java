package com.peng.chapter7;

import com.peng.chapter7.proxy.Proxy;
import com.peng.chapter7.proxy.Pursuit;
import com.peng.chapter7.proxy.SchoolGirl;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Auther lovely
 * @Create 2020-04-28 10:18
 * @Description
 * 代理模式(Proxy)为其他对象提供一种代理以控制对这个对象的访问
 * 远程代理：也就是为一个对象在不同的地址空间提供局部代表。这个可以隐藏一个对象存于不同地址空间的事实
 * 虚拟代理，是根据需要创建开销很大的对象。通过它来存放实例化很长时间的真实对象
 * 安全代理：用来控制真实对象访问的权限
 * 智能引用：是指当调用真实的对象时，代理处理其他一些事。
 * 代理就是真实对象的代表
 */
public class ProxyTest {
    public static void main(String[] args) {
        SchoolGirl schoolGirl = new SchoolGirl("0.0");
        Pursuit pursuit = new Pursuit(schoolGirl, "._.");

        Proxy.run(pursuit);

    }
}
