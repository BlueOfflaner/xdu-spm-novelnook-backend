package com.xdu.nook.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.vo.UserInfoVo;

import java.util.List;


/**
* @author violet
* @description 针对表【sys_info】的数据库操作Service
* @createDate 2023-04-06 21:46:35
*/
public interface SysInfoService extends IService<SysInfo> {

    public SysInfo getSysInfoByEmail(String email);

    void modifyStatus(String email, Integer permission, Integer isAvailable);

    SysInfo getSysInfoByUserId(Long userId);

    void modifyStatusBulk(List<UserInfoVo> userInfoVoList);

}
