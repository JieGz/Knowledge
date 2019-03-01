package com.demo.mq.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author 揭光智
 * @date 2019/03/01
 */
public class MqClient {
    /**
     * 生产消息
     *
     * @param message 消息
     */
    public static void produce(String message) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println(message);
            out.flush();
        }
    }

    /**
     * 消费消息
     *
     * @return 消息
     */
    public static String consume() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println("CONSUME");
            out.flush();
            return in.readLine();
        }
    }

    public static void main(String[] args) throws Exception {
        //  MqClient.produce("Hello,World");
        System.out.println("获取的消息:" + MqClient.consume());
    }
}
