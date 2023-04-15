package com.xdu.nook.user.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
        //请求页面大于最大页时，返回首页而非空
        paginationInterceptor.setOverflow(true);
        //设置最大单页数量，-1表示不受限
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }
}
