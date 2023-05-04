package com.xdu.nook.material;

import com.xdu.nook.material.service.NavigationService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Resource
    NavigationService navigationService;

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @org.junit.Test
    public void testRocketMQTemplate(){
        rocketMQTemplate.convertAndSend("nook","这是一条简单的消息");
    }
    // broker(代理，中间商，掮客)
}
