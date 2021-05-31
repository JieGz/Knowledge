package com.demo.driver;

import java.util.List;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public interface IMessageQueue<MSG> {

    int DEFAULT_INITIAL_CAPACITY = 2048;

    /**
     * 消息队列接收一条消息
     *
     * @param message 消息
     */
    void receiveMessage(MSG message);

    void receiveMessage(List<MSG> messages);

    /**
     * 从消息队列中获取一条消息
     *
     * @return 消息
     */
    MSG getMessage();
}
