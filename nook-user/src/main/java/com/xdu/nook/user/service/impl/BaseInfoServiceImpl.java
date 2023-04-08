package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.BaseInfo;
import com.xdu.nook.user.service.BaseInfoService;
import com.xdu.nook.user.mapper.BaseInfoMapper;
import com.xdu.nook.user.vo.BaseInfoVo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author violet
* @description 针对表【base_info】的数据库操作Service实现
* @createDate 2023-04-06 21:51:11
*/
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoMapper, BaseInfo>
implements BaseInfoService{

    public BaseInfo getBaseInfoByUserId(Long userId) {
        LambdaQueryWrapper<BaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userId != null, BaseInfo::getUserId, userId);
        BaseInfo baseInfo = this.getOne(queryWrapper);
        return baseInfo;
    }

    public void initBaseInfo(BaseInfoVo baseInfoVo) {
        BaseInfo baseInfo = this.getBaseInfoByUserId(baseInfoVo.getId());
        baseInfo.setUkIdCode(baseInfoVo.getUkid());
        baseInfo.setName(baseInfoVo.getNickname());
        baseInfo.setBirthday(baseInfoVo.getBirthday());

        this.updateById(baseInfo);
    }
}
