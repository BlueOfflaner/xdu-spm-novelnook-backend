package com.xdu.nook.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.dto.UserBaseInfoDto;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.vo.UserInfoVo;

import java.util.List;


/**
* @author violet
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-06 21:51:11
*/
public interface UserService extends IService<User> {

    public UserBaseInfoDto welcomeUser(String email);

    public User getUserBySysInfo(SysInfo sysInfo);

    public UserInfoVo getOneUser(Long id);

    public List<UserInfoVo> getUserInfoAll();

    Page getUserInfoList(Integer pageSize, Integer currentPage);
}
