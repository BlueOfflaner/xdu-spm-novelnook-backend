package com.xdu.nook.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.user.entity.BaseInfo;


/**
* @author 21145
* @description 针对表【base_info】的数据库操作Service
* @createDate 2023-04-06 21:51:11
*/
public interface BaseInfoService extends IService<BaseInfo> {

    public BaseInfo getBaseInfoByUserId(Long userId);
}
