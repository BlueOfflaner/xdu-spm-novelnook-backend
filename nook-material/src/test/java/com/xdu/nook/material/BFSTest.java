package com.xdu.nook.material;

import com.xdu.nook.material.service.NavigationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BFSTest {
    @Resource
    NavigationService navigationService;

    @Test
    public void testBfs(){
        navigationService.getNavigationList();
    }
}
