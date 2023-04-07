package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @author 21145
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
    public void welcomeUser(String email) {
        SysInfo sysInfo = sysInfoService.getSysInfoByEmail(email);

        if(sysInfo == null) {
            sysInfo = new SysInfo();
            sysInfo.setEmail(email);
            Date time= new Date();
            sysInfoService.save(sysInfo);

            User user = new User();
            user.setSysInfoId(sysInfo.getId());
            sysInfo.setUserId(user.getId());
            this.save(user);

            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setUserId(user.getId());
            baseInfoService.save(baseInfo);

            sysInfo.setUserId(user.getId());
            sysInfoService.updateById(sysInfo);
            user.setBaseInfoId(baseInfo.getId());
            this.updateById(user);
        }else{

        }

    }



}
