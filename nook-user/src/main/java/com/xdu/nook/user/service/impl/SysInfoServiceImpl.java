package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.mapper.SysInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author 21145
 * @description 针对表【sys_info】的数据库操作Service实现
 * @createDate 2023-04-06 21:46:35
 */
@Service
public class SysInfoServiceImpl extends ServiceImpl<SysInfoMapper, SysInfo> implements SysInfoService {

}
