package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.BaseInfo;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.mapper.BaseInfoMapper;
import com.xdu.nook.user.mapper.SysInfoMapper;
import com.xdu.nook.user.service.UserService;
import com.xdu.nook.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

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
    SysInfoMapper sysInfoMapper;

    @Resource
    BaseInfoMapper baseInfoMapper;
    /**
     * 判断某一邮箱对应用户是否已经存在，若不存在，则创建新user，入库
     * @param email 这表明，需要判断的对象是一个邮箱
     */
    public void welcomeUser(String email) {
        SysInfo sysInfo = getSysInfoByEmail(email);

        if(sysInfo == null) {
            sysInfo = new SysInfo();
            sysInfo.setEmail(email);
            sysInfo.setCreateTime(new Date(System.currentTimeMillis()));
            sysInfo.setUpdateTime(new Date(System.currentTimeMillis()));
            sysInfoMapper.insert(sysInfo);

            BaseInfo baseInfo = new BaseInfo();
            baseInfoMapper.insert(baseInfo);

            User user = new User();
            user.setCreateTime(new Date(System.currentTimeMillis()));
            user.setUpdateTime(new Date(System.currentTimeMillis()));
            user.setSysInfoId(sysInfo.getId());
            user.setBaseInfoId(baseInfo.getId());
            this.save(user);

            sysInfo.setUserId(user.getId());
            sysInfoMapper.updateById(sysInfo);
            baseInfo.setUserId(user.getId());
            baseInfoMapper.updateById(baseInfo);
        }

    }

    @Override
    public SysInfo getSysInfoByEmail(String email) {
        LambdaQueryWrapper<SysInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(email != null, SysInfo::getEmail, email);
        SysInfo sysInfo = sysInfoMapper.selectOne(queryWrapper);
        return sysInfo;
    }

}
