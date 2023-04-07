package com.xdu.nook.authserver.controller;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.constant.RedisIndex;
import com.xdu.nook.api.utils.R;

import com.xdu.nook.authserver.service.SendCodeService;
import com.xdu.nook.authserver.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        String s = RedisIndex.LOGIN_CODE + to;

        String res_user_code = redisTemplate.opsForValue().get(s);

        System.out.println(res_user_code);
        log.info(res_user_code);

        if (null == res_user_code) {
            String code = (String) sendCodeService.send(to).get("data");
            redisTemplate.opsForValue().set(s, code, 60, TimeUnit.SECONDS);
        }

        String ret_msg = "成功向" + to + "发送验证码";
        return R.ok(ret_msg);
    }

    //TODO 有待处理异常
    @PostMapping("/regist")
    public R testRegist(@RequestBody HashMap<String, String> request) throws ParseException {
        String email = request.get("email");
        String code = request.get("code");
        if (code == null || email == null) R.error(ERCode.PARAM_ERR.getCode(), ERCode.PARAM_ERR.getItem());
        String realCode = redisTemplate.opsForValue().get(RedisIndex.LOGIN_CODE + email);
        if (null == realCode) {
            return R.error(ERCode.VERTIF_CODE_ERR.getCode(), ERCode.VERTIF_CODE_ERR.getItem());
        } else {
            if (code.equals(realCode)) {
                //TODO 此处需要重新设计token生成方案
                UserInfoVo userinfo = new UserInfoVo();
                userinfo.setToken("612729200104055712");
                userinfo.setName("IsabellaViolet");
                userinfo.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2001-04-05"));

                return R.ok("登录成功", userinfo);
            } else {
                return R.error(ERCode.VERTIF_CODE_ERR.getCode(), ERCode.VERTIF_CODE_ERR.getItem());
            }
        }

    }
}
