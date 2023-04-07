package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.mapper.SysInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author violet
 * @description 针对表【sys_info】的数据库操作Service实现
 * @createDate 2023-04-06 21:46:35
 */
@Service
public class SysInfoServiceImpl extends ServiceImpl<SysInfoMapper, SysInfo> implements SysInfoService {
    public SysInfo getSysInfoByEmail(String email) {
        LambdaQueryWrapper<SysInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(email != null, SysInfo::getEmail, email);
        SysInfo sysInfo = this.getOne(queryWrapper);
        return sysInfo;
    }
}
