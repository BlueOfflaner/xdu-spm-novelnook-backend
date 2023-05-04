package com.xdu.nook.authserver.controller;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.constant.RedisIndex;
import com.xdu.nook.api.utils.R;

import com.xdu.nook.authserver.dto.RegistDto;
import com.xdu.nook.authserver.dto.UserBaseInfoDto;
import com.xdu.nook.authserver.feign.UserClient;
import com.xdu.nook.authserver.service.CheckVerificationCode;
import com.xdu.nook.authserver.service.SendCodeService;
import com.xdu.nook.authserver.service.impl.CheckVerificationCodeImpl;
import com.xdu.nook.authserver.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
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
    @Resource
    UserClient userClient;

    @Resource
    CheckVerificationCode checkVerificationCode;

    @GetMapping("/get-code")
    public R sendCode(String to) {

        String s = RedisIndex.LOGIN_CODE + to;

        String res_user_code = redisTemplate.opsForValue().get(s);
        if (null == res_user_code) {
            String code = (String) sendCodeService.send(to).get("data");
            redisTemplate.opsForValue().set(s, code, 600, TimeUnit.SECONDS);
        }
        String ret_msg = "成功向" + to + "发送验证码";
        return R.ok(ret_msg);
    }


    @PostMapping("/regist")
    public R testRegist(@RequestBody HashMap<String, String> request) throws ParseException {
        String email = request.get("email");
        String code = request.get("code");
        String password = request.get("password");
        if(email==null){
            return R.error(ERCode.PARAM_ERR);
        }else{
            if(code ==null){
                if(password==null){
                    return R.error(ERCode.PARAM_ERR);
                }else{
                    //ep


                    UserBaseInfoDto userBaseInfoDto = userClient.loginWithPassword(email, password);
                    if( userBaseInfoDto==null ||userBaseInfoDto.getId()==null){
                        return R.error(ERCode.LOGIN_INFO_ERR);
                    }else {
                        UserInfoVo userInfoVo = new UserInfoVo();
                        BeanUtils.copyProperties(userBaseInfoDto,userInfoVo);

                        return R.ok(userInfoVo);
                    }
                }
            }else{
                Boolean flag = checkVerificationCode.test(email, code);
                //ec
                if (flag) {

                    System.out.println("email:"+email+" | password："+password+" | flag："+flag);

                    RegistDto registDto = new RegistDto(email, password);
                    UserBaseInfoDto userBaseInfoDto = userClient.welcomeUser(registDto);
                    UserInfoVo userInfoVo = new UserInfoVo();
                    BeanUtils.copyProperties(userBaseInfoDto, userInfoVo);
                    userInfoVo.setToken("612729200104055712");
                    return R.ok(userInfoVo);
                } else {
                    return R.error();
                }
            }
        }
    }
}
