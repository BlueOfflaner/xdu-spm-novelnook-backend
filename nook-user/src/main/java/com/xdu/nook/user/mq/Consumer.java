package com.xdu.nook.user.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "materialConsumer",topic = "Reminder")
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        int i=0;
        System.out.println("这是我第几次接收到消息呢:"+(i++));
    }
}
