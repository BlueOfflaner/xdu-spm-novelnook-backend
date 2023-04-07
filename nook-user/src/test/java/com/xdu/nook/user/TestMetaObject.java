package com.xdu.nook.user;

import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMetaObject {
    @Resource
    UserMapper userMapper;
    @Test
    public void testObject() {
        userMapper.insert(new User());
    }
}
