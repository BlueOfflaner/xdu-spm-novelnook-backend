package com.xdu.nook.message.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Reminder {
    @Resource
    RocketMQTemplate rocketMQTemplate;

    public void arrearsRemind(String msg){
        rocketMQTemplate.convertAndSend("Reminder",msg);
    }

}
