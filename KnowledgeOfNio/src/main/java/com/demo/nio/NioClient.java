package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author 揭光智
 * @date 2019/02/07
 */
public class NioClient {


    /**
     * 缓冲区的大小
     */
    private final int BLOCK_SIZE = 4096;
    /**
     * 发送数据的缓冲区
     */
    private ByteBuffer sendBuff = ByteBuffer.allocate(BLOCK_SIZE);

    /**
     * 接收数据的缓冲区
     */
    private ByteBuffer receiveBuff = ByteBuffer.allocate(BLOCK_SIZE);


    /**
     * 选择器
     */
    private Selector selector;


    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.listen();

    }

    public NioClient() throws IOException {
        //客户端通道
        SocketChannel socketChannel = SocketChannel.open();
        //非阻塞
        socketChannel.configureBlocking(false);
        //打开选择器
        selector = Selector.open();
        socketChannel.connect(new InetSocketAddress(8888));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        System.out.println("客户端开始连接！");
    }

    /**
     * 监听
     *
     * @throws IOException IOException
     */
    public void listen() throws IOException {
        while (true) {
            if (this.selector.select() < 0) {
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handleKey(key);
            }
        }
    }

    /**
     * 处理逻辑
     *
     * @param key
     */
    private void handleKey(SelectionKey key) throws IOException {
        SocketChannel socketChannel;
        String receiveStr;
        String sendStr;
        int count = 0;
        if (key.isConnectable()) {
            System.out.println("客户端连接");
            socketChannel = (SocketChannel) key.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                System.out.println("客户端连接完成!");
                sendBuff.clear();
                sendStr = "你好，我是客户端007,现在几点了？";
                sendBuff.put(sendStr.getBytes());
                sendBuff.flip();
                socketChannel.write(sendBuff);

                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } else if (key.isReadable()) {
            socketChannel = (SocketChannel) key.channel();
            receiveBuff.clear();
            count = socketChannel.read(receiveBuff);
            if (count > 0) {
                receiveStr = new String(receiveBuff.array());
                System.out.println("客户端接收到服务的消息：====>" + receiveStr);
            }

        }
    }
}
