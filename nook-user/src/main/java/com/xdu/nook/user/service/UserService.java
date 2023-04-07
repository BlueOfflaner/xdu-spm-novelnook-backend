package com.xdu.nook.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.dto.UserBaseInfoDto;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.entity.User;


/**
* @author 21145
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-06 21:51:11
*/
public interface UserService extends IService<User> {

    public UserBaseInfoDto welcomeUser(String email);

    public User getUserBySysInfo(SysInfo sysInfo);
}
