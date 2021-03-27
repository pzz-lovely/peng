package com.peng.chapter16.state1;

/**
 * @Auther lovely
 * @Create 2020-04-29 10:28
 * @Description 下午工作
 */
public class NoonState extends State {
    @Override
    public void writeProgram(Work work) {
        if(work.getHour() < 12)
            System.out.println("当前时间：" + work.getHour() + "点 下午工作，精神百倍");

    }
}
