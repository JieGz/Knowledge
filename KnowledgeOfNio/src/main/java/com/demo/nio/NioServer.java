package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 揭光智
 * @date 2019/02/07
 */
public class NioServer {

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


    public NioServer(int port) throws IOException {

        //通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //非阻塞
        serverSocketChannel.configureBlocking(false);

        //获取服务端的socket,并绑定端口
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        //打开选择器
        selector = Selector.open();

        //向选择器注册接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务端开始监听" + port + "端口");
    }

    /**
     * 监听
     *
     * @throws IOException IOException
     */
    public void listen() throws IOException {
        System.out.println("服务器已启动成功");
        while (true) {
            if (this.selector.select() < 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
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
        ServerSocketChannel serverSocketChannel;
        SocketChannel socketChannel;
        String receiveStr;
        String sendStr;
        int count = 0;
        if (key.isValid()) {
            if (key.isAcceptable()) {
                serverSocketChannel = (ServerSocketChannel) key.channel();
                socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                socketChannel = (SocketChannel) key.channel();
                receiveBuff.clear();
                count = socketChannel.read(receiveBuff);
                if (count > 0) {
                    receiveStr = new String(receiveBuff.array());
                    System.out.println("服务端接收到来自客户端的信息：====>" + receiveStr);
                    socketChannel.register(selector, SelectionKey.OP_WRITE);

                    sendBuff.clear();
                    sendStr = new Date().toString();
                    sendBuff.put(sendStr.getBytes());
                    sendBuff.flip();
                    socketChannel.write(sendBuff);
                    System.out.println("服务端发送给客户端：" + sendStr);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer(8888);
        server.listen();
    }
}
