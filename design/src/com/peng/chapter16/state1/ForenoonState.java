package com.peng.chapter16.state1;

/**
 * @Auther lovely
 * @Create 2020-04-29 10:28
 * @Description 上午和中午状态类
 */
public class ForenoonState extends State {
    @Override
    public void writeProgram(Work work) {
        if(work.getHour() < 12)
            System.out.println("当前时间：" + work.getHour() + "点 中午工作，精神百倍");
        else{
            work.setState(new NoonState());
            work.writeProgram();
        }

    }
}
