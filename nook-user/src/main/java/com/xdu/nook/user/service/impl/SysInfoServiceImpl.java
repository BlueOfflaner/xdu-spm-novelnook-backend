package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.mapper.SysInfoMapper;
import com.xdu.nook.user.vo.UserInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author violet
 * @description 针对表【sys_info】的数据库操作Service实现
 * @createDate 2023-04-06 21:46:35
 */
@Service
public class SysInfoServiceImpl extends ServiceImpl<SysInfoMapper, SysInfo> implements SysInfoService {

    //TODO 将
    public void modifyStatus(String email, Integer permission, Integer isAvailable) {


        SysInfo sysInfo = this.getOne(new LambdaQueryWrapper<SysInfo>()
                .eq(email!=null,
                        SysInfo::getEmail,
                        email));
        sysInfo.setPermission(permission);
        sysInfo.setIsAvailable(isAvailable);
        this.updateById(sysInfo);
    }

    public void modifyStatusBulk(List<UserInfoVo> userInfoVoList) {
        userInfoVoList.forEach(userInfoVo -> {
            modifyStatus(userInfoVo.getEmail(), userInfoVo.getPermission(), userInfoVo.getIsAvailable());
        });
    }

    public SysInfo getSysInfoByUserId(Long userId) {
        LambdaQueryWrapper<SysInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userId != null, SysInfo::getUserId, userId);
        SysInfo sysInfo = this.getOne(queryWrapper);
        return sysInfo;
    }

    public SysInfo getSysInfoByEmail(String email) {
        //TODO 待处理疑点
        LambdaQueryWrapper<SysInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(email != null, SysInfo::getEmail, email);
        List<SysInfo> list = this.list(queryWrapper);

        SysInfo sysInfo = this.getOne(queryWrapper);
        return sysInfo;
    }
}
