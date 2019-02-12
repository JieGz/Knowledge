package com.demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author 揭光智
 * @date 2019/02/11
 */
public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer();
        ByteBuf byteBuf = buffer.writeInt(10);
        int i = byteBuf.readInt();
        System.out.println(i);

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes("I'm Je!".getBytes());
        System.out.println(buf.toString(CharsetUtil.UTF_8));

    }
}
