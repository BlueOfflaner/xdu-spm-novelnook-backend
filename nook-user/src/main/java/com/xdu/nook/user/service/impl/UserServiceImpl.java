package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.service.UserService;
import com.xdu.nook.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 21145
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-06 21:51:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}