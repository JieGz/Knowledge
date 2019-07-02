package com.demo.json;

import com.demo.json.entity.Entity;
import com.demo.json.entity.LeiTai;
import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 揭光智
 * @date 2019/05/07
 */
public class Main extends WebSocketClient {
    public static void main(String[] args) throws Exception {

        //注意注意,并不是每次都能拿到数据的，需要多尝试
        Main client = new Main(new URI("wss://publicld.gwgo.qq.com?account_value=0&account_type=0&appid=0&token=0"));
        client.connect();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(1559563200 * 1000L);
        System.out.println(System.currentTimeMillis());
        String time = format.format(date);
        System.out.println(time);
    }


    public Main(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        Entity entity = new Entity();
        entity.setRequest_type("1002");
        entity.setLongtitude(113324773);
        entity.setLatitude(23119675);
        entity.setRequestid(requestId());
        entity.setPlatform(0);
        send(covertData(entity));
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

        System.out.println(parseData(message.array()));

        LeiTai leiTai = new Gson().fromJson(parseData(message.array()), LeiTai.class);
//        System.out.println(leiTai);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    private long requestId() {
        return System.currentTimeMillis() % 1234567;
    }


    private byte[] covertData(Entity entity) {
        String json = new Gson().toJson(entity);
        System.out.println(json);
        byte[] jsonBytes = json.getBytes();
        byte[] data = new byte[jsonBytes.length + 4];
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        data[3] = (byte) jsonBytes.length;
        System.arraycopy(jsonBytes, 0, data, 4, jsonBytes.length);
        return data;
    }

    private String parseData(byte[] array) {
        byte[] data = new byte[array.length - 4];
        System.arraycopy(array, 4, data, 0, array.length - 4);
        return new String(data);
    }
}
