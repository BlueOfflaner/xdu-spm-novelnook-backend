package com.xdu.nook.message.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {
    @Resource
    RocketMQTemplate rocketMQTemplate;

    public void arrearsRemind(String msg){
        rocketMQTemplate.convertAndSend("Reminder",msg);
    }

    public void sendInformation(String msg){
        rocketMQTemplate.convertAndSend("Material",msg);
        rocketMQTemplate.convertAndSend("User",msg);
    }

}
