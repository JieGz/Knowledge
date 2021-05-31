package com.demo.driver.queue;

import com.demo.driver.IMessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public abstract class AbstractMessageQueue<MSG> implements IMessageQueue<MSG> {

    @Override
    public void receiveMessage(MSG message) {
        if (message == null) return;
        List<MSG> messages = new ArrayList<>();
        doReceiveMessage(messages);
    }

    @Override
    public void receiveMessage(List<MSG> messages) {
        if (messages == null || messages.size() == 0) return;
        doReceiveMessage(messages);
    }

    @Override
    public MSG getMessage() {
        return doGetMessage();
    }

    protected abstract void doReceiveMessage(List<MSG> message);

    protected abstract MSG doGetMessage();
}
