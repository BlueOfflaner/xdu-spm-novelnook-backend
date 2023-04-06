package com.xdu.nook.thirdware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ThirdWareApplication {
    public static void main(String[] args){
        SpringApplication.run(ThirdWareApplication.class, args);
    }
}