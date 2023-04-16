package com.xdu.nook.message.test.feign;

import com.alibaba.fastjson.JSONArray;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.message.feign.UserClient;
import com.xdu.nook.message.vo.UserInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {
    @Resource
    UserClient userClient;

    @Test
    public void test() {
    }
}
