package com.xdu.nook.authserver.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.163.130:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
