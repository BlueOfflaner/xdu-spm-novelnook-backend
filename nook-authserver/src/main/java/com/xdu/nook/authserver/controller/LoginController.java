package com.xdu.nook.authserver.controller;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.constant.RedisIndex;
import com.xdu.nook.api.utils.R;

import com.xdu.nook.authserver.service.SendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
        String s = RedisIndex.LOGIN_CODE + to;
        log.info(s);

        String res_user_code = redisTemplate.opsForValue().get(s);
        log.info(res_user_code);
        if (null == res_user_code) {
            redisTemplate.opsForValue().set(s, code, 10, TimeUnit.SECONDS);
        }

        String ret_msg = "成功向" + to + "发送验证码";
        return R.ok(ret_msg);
    }

    @PostMapping("/regist")
    public R testRegist(@RequestBody HashMap<String,String> request ){
        String email = request.get("email");
        String code = request.get("code");
        //TODO 异常码
        if(code ==null || email ==null)R.error(ERCode.PARAM_ERR.getCode(),ERCode.PARAM_ERR.getItem());
        String realCode = redisTemplate.opsForValue().get(RedisIndex.LOGIN_CODE + email);
        if(null ==realCode){
            //TODO 异常码
            return R.error();
        }else {
            if(code.equals(realCode)){
                //TODO 返回逻辑：应当返回登录用户的id
                return R.ok("登录成功",true);
            }else{
                return R.error();
            }
        }

    }
}
