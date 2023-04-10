package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.service.BaseInfoService;
import com.xdu.nook.material.mapper.BaseInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21145
* @description 针对表【base_info】的数据库操作Service实现
* @createDate 2023-04-10 18:42:29
*/
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoMapper, BaseInfoEntity>
    implements BaseInfoService{

    public List<BaseInfoEntity> getBaseInfoByIsbn(IsbnInfoEntity isbnInfo) {
        LambdaQueryWrapper<BaseInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != isbnInfo.getId(), BaseInfoEntity::getIsbnInfoId, isbnInfo.getId());
        List<BaseInfoEntity> list = this.list(queryWrapper);
        return list;
    }
}




