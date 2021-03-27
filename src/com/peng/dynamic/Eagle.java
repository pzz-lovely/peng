package com.peng.dynamic;

/**
 * @Author lovely
 * @Create 2020-12-24 14:25
 * @Description 老鹰
 */
public class Eagle  implements Animal,Bird {

    // 必须依赖 翅膀才可以 飞翔
    // 把翅膀 组合到 鹰
    private Wing wing;

    public Wing getWing() {
        return wing;
    }

    public void setWing(Wing wing) {
        this.wing = wing;
    }

    @Override
    public String run() {
        return "鹰在地上跑";
    }

    @Override
    public String fly() {
        return wing.fly();
    }
}