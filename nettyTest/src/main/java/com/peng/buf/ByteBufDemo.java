package com.peng.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author lovely
 * @Create 2020-11-03 12:04
 * @Description todo
 */
public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.directBuffer(512);
        System.out.println(buf);


        // SimpleLeakAwareByteBuf(UnpooledUnsafeDirectByteBuf(ridx:0, width: 0, cap: 512))
        // SimpleLeakAwareByteBuf包装类，使用了装饰模式，内部维护了一个 UnpooledUnsafeDirectByteBuf
        // 该类与 UnpooledDirectByteBuf 类似。首选在创建 SimpleLeakAwareByteBuf的时候，会将该引用加入到内存泄漏检测的引用链中

        buf.release();

    }
}