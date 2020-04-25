package com.demo.netty.http.constance;

/**
 * @author 揭光智
 * @date 2020/04/25
 */
public class Constance {

    /** 入站消息空闲时间,会触发空闲回调 */
    public static final long READER_IDLE_TIME = 20;

    /** 出站消息空闲时间,会触发空闲回调 */
    public static final long WRITER_IDLE_TIME = 10;

    /** 出入站消息空时间,会触发空闲回调 */
    public static final long ALL_IDLE_TIME = 30;
}
