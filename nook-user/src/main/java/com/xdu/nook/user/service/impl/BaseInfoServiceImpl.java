package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.BaseInfo;
import com.xdu.nook.user.service.BaseInfoService;
import com.xdu.nook.user.mapper.BaseInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author violet
* @description 针对表【base_info】的数据库操作Service实现
* @createDate 2023-04-06 21:51:11
*/
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoMapper, BaseInfo>
implements BaseInfoService{

    @Override
    public BaseInfo getBaseInfoByUserId(Long userId) {
        LambdaQueryWrapper<BaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userId != null, BaseInfo::getUserId, userId);
        BaseInfo baseInfo = this.getOne(queryWrapper);
        return baseInfo;
    }
}
