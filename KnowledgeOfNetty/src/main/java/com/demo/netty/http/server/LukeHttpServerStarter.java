package com.demo.netty.http.server;

/**
 * @author 揭光智
 * @date 2020/04/22
 */
public class LukeHttpServerStarter {

    public static void main(String[] args) throws Exception {
        new LukeHttpServer(9090).start();
    }
}
