package com.peng.chapter16.state1;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:00
 * @Description
 * 状态模式主要解决的是当控制一个对象状态转换的跳进表达式过于复杂的情况下。把这个状态判断逻辑转移到不同状态的一系列类中，可以把复杂的判断逻辑简化
 * 将与特定状态相关的行为局部化，并且将不同状态的行为分割开来
 */
public class StateTest {
    public static void main(String[] args) {
        Work work = new Work();
        work.writeProgram();
    }
}
