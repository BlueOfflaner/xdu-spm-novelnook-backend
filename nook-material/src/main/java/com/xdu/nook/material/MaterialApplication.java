package com.xdu.nook.material;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication()
public class MaterialApplication {
    public static void main(String[] args){
        SpringApplication.run(MaterialApplication.class, args);
    }
}