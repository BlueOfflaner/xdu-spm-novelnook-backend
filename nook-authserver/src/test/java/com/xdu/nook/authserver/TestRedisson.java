package com.xdu.nook.authserver;

import com.xdu.nook.authserver.service.CodeFactory;
import com.xdu.nook.authserver.service.SendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisson {

    @Resource
    RedissonClient redissonClient;
    @Resource(name="stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    @Resource
    CodeFactory cf;


    @Resource
    SendCodeService sendCodeService;
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","violet");
    }

    @Test
    public void testCode(){
        for (int i=0;i<20;i++){
            String s = cf.generateCode();
            log.info(s);
        }

    }

    @Test
    public void testSend(){
        sendCodeService.send("1765017394@qq.com");
    }
}
