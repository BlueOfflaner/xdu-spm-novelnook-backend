package com.xdu.nook.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 21145
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-04-06 21:51:11
 * @Entity com.xdu.nook.user.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserInfoVo> getUserInfoAll();
}
