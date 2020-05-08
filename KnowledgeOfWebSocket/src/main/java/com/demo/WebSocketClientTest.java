package com.demo;

import org.java_websocket.WebSocketImpl;
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
        WebSocketImpl.DEBUG = true;
        WebSocketClient client = new WebSocketClientTest(new URI("ws://127.0.0.1:8888/ws?token=eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhcHAtaXQtYnJhaW4taG9sZCIsImlhdCI6MTU4ODY0ODczMiwiYXVkIjoibGl1cWlhbmciLCJzdWIiOiJ7XCJhY2NvdW50XCI6XCJsaXVxaWFuZ1wiLFwiYXV0aG9yaXphdGlvbnNcIjpbXCJST0xFX1NZU1RFTVwiLFwiUk9MRV9VU0VSX01BTkFHRVJcIixcIlJPTEVfQUNDT1VOVF9JTkZPXCIsXCJST0xFX1RBU0tfQ0hBSU5cIixcIlJPTEVfRlVOQ1RJT05cIixcIlJPTEVfQ0hBVF9DT05URU5UXCIsXCJST0xFX1BIT05FX0lORk9cIixcIlJPTEVfQURNSU5cIl19IiwiZXhwIjoxNTkwMTE5OTYwfQ.2b8yLapSS1GvNTV45dN5WB0xYihBetjYYt4uFd4ymxD-54O-NBJBocDwE_qop6ZIjY0ik64oxqlMSAZznDg_ZA"));
        client.connect();
    }
}
