package com.xdu.nook.message.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

@RocketMQMessageListener(consumerGroup = "messageGroup", topic = "message")
public class Listener implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {

    }
}
