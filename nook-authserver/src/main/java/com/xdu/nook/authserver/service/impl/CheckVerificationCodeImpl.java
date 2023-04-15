package com.xdu.nook.authserver.service.impl;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.constant.RedisIndex;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.authserver.dto.UserBaseInfoDto;
import com.xdu.nook.authserver.service.CheckVerificationCode;
import com.xdu.nook.authserver.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckVerificationCodeImpl implements CheckVerificationCode {
    @Resource(name="stringRedisTemplate")
    StringRedisTemplate redisTemplate;
    public Boolean test(String email,String code){
        String realCode =  redisTemplate.opsForValue().get(RedisIndex.LOGIN_CODE + email);
        if (null == realCode) {
            return false;
        } else {
            if (code.equals(realCode)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
