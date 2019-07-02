package com.demo;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2019/06/18
 */
public class WebSocketServerCloseTest extends WebSocketServer {


    public WebSocketServerCloseTest(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake){
        //This method sends a message to the new client
        conn.send("Welcome to the server!");
        //This method sends a message to all clients connected
        broadcast("new connection: " + handshake.getResourceDescriptor());
        System.out.println("new connection to " + conn.getRemoteSocketAddress());

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("received message from " + conn.getRemoteSocketAddress() + ": " + message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occured on connection " + conn.getRemoteSocketAddress() + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }





    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8887;
        WebSocketServer server = new WebSocketServerCloseTest(new InetSocketAddress(host, port));
        server.setReuseAddr(true);
        server.run();


    }


}
