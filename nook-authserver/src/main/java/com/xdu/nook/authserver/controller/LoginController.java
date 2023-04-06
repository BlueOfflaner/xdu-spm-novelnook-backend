package com.xdu.nook.authserver.controller;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.authserver.service.SendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@ResponseBody
@RequestMapping("/login")
public class LoginController {
    @Resource
    SendCodeService sendCodeService;
    @Resource(name = "stringRedisTemplate")
    StringRedisTemplate redisTemplate;

    @GetMapping("/get-code")
    public R sendCode(String to) {
        String code = (String) sendCodeService.send(to).get("data");
        log.info(code);
        String s = "login:code:" +
                to;
        log.info(s);

        String res_user_code = redisTemplate.opsForValue().get(s);
        if(null == res_user_code){
            redisTemplate.opsForValue().set(s, code,60, TimeUnit.SECONDS);
        }

        String ret_msg = "成功向" + to + "发送验证码";
        return R.ok(ret_msg);
    }
}
