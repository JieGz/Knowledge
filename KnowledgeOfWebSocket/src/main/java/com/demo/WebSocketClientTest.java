package com.demo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

/**
 * @author 揭光智
 * @date 2019/06/18
 */
public class WebSocketClientTest extends WebSocketClient {

    public WebSocketClientTest(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebSocketClientTest(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        //send("Hello, it is me. Mario :)");
        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient client = new WebSocketClientTest(new URI("ws://localhost:11111?token=eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhcHAtaXQtYnJhaW4taG9sZCIsImlhdCI6MTU2MTA5NzA3NSwiYXVkIjoiMTM1NzA1Nzg0MTciLCJzdWIiOiJ7XCJhdXRob3JpemF0aW9uc1wiOltcIlJPTEVfVVNFUlwiLFwiUk9MRV9BRE1JTlwiXX0ifQ.Nc5txM5a9Vsdb4Z2mxupajM03alNp2hoHeH51k4Fxjl1nv-ag8jtVCfiYPdvTgLOV2RxHPQtBkiftWaYLH9n-g"));
        client.connect();
    }
}
