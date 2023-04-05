package com.xdu.nook.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient
public class AuthServerApplication {
    public static void main(String[] args){
        SpringApplication.run(AuthServerApplication.class, args);
    }
}