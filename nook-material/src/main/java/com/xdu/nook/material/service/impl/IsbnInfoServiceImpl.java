package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.service.IsbnInfoService;
import com.xdu.nook.material.mapper.IsbnInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 21145
* @description 针对表【isbn_info】的数据库操作Service实现
* @createDate 2023-04-10 18:42:29
*/
@Service
public class IsbnInfoServiceImpl extends ServiceImpl<IsbnInfoMapper, IsbnInfoEntity>
    implements IsbnInfoService{
    @Resource
    IsbnInfoService isbnInfoService;

    @Override
    public IsbnInfoEntity getIsbnWithBaseInfoService(BaseInfoEntity baseInfoEntity) {
        Long isbnInfoId = baseInfoEntity.getIsbnInfoId();
        IsbnInfoEntity byId = isbnInfoService.getById(isbnInfoId);
        return byId;
    }
}




