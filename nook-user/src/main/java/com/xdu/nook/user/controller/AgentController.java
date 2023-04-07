package com.xdu.nook.user.controller;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.dto.UserBaseInfoDto;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Resource
    UserService userService;
    @GetMapping("welcome/{email}")
    public R welcomeUser(@PathVariable String email){
        UserBaseInfoDto userBaseInfoDto = userService.welcomeUser(email);
        return R.ok(userBaseInfoDto);
    }
}
