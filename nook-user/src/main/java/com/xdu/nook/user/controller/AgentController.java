package com.xdu.nook.user.controller;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Resource
    UserService userService;
    @GetMapping("welcome")
    public R welcomeUser(){

        return R.error();
    }
}
