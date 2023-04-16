package com.xdu.nook.thirdware.service;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOss {
    @Resource
    OSSClient ossClient;

    public static final String bucketName="nook-violet";

    public static final String access_key = "LTAI5tDDzVwswJAmTEiMxQKd";


    @Test
    public void test(){
        ossClient.putObject(bucketName,"紫罗兰永恒花园主题头像.jpg",new File("C:\\myfile\\document\\picture\\QQ图片20230413182947.jpg"));
        ossClient.shutdown();
    }

}
