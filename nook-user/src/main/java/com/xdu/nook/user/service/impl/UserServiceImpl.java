package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.xdu.nook.user.dto.UserBaseInfoDto;
import com.xdu.nook.user.entity.BaseInfo;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.mapper.BaseInfoMapper;
import com.xdu.nook.user.mapper.SysInfoMapper;
import com.xdu.nook.user.service.BaseInfoService;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.service.UserService;
import com.xdu.nook.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author violet
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-04-06 21:51:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    SysInfoService sysInfoService;

    @Resource
    BaseInfoService baseInfoService;
    /**
     * 判断某一邮箱对应用户是否已经存在，若不存在，则创建新user，入库
     * @param email 这表明，需要判断的对象是一个邮箱
     */
    @Transactional
    public UserBaseInfoDto welcomeUser(String email) {
        SysInfo sysInfo = sysInfoService.getSysInfoByEmail(email);

        BaseInfo baseInfo = new BaseInfo();

        if(null == sysInfo) {
            sysInfo = new SysInfo();
            sysInfo.setEmail(email);
            sysInfoService.save(sysInfo);

            User user = new User();
            user.setSysInfoId(sysInfo.getId());
            this.save(user);

            //BaseInfo baseInfo = new BaseInfo();
            baseInfo.setUserId(user.getId());
            baseInfoService.save(baseInfo);

            sysInfo.setUserId(user.getId());
            sysInfoService.updateById(sysInfo);
            user.setBaseInfoId(baseInfo.getId());
            this.updateById(user);
        }

        baseInfo = baseInfoService.getBaseInfoByUserId(sysInfo.getUserId());
        UserBaseInfoDto userBaseInfoDto = new UserBaseInfoDto();
        packUserBaseInfoDto(userBaseInfoDto, baseInfo, sysInfo);
        return userBaseInfoDto;
    }

    @Override
    public User getUserBySysInfo(SysInfo sysInfo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(sysInfo.getUserId() != null, User::getId, sysInfo.getUserId());
        User user = this.getOne(queryWrapper);
        return user;
    }

    private void packUserBaseInfoDto(UserBaseInfoDto userBaseInfoDto, BaseInfo baseInfo, SysInfo sysInfo) {
        userBaseInfoDto.setId(sysInfo.getUserId());
        userBaseInfoDto.setUKIDCode(baseInfo.getUkIdCode());
        userBaseInfoDto.setName(baseInfo.getName());
        userBaseInfoDto.setBirthday(baseInfo.getBirthday());
        userBaseInfoDto.setEmail(sysInfo.getEmail());
    }
}
