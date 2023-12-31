package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.BaseInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.material.entity.IsbnInfoEntity;

import java.util.List;

/**
* @author 21145
* @description 针对表【base_info】的数据库操作Service
* @createDate 2023-04-10 18:42:29
*/
public interface BaseInfoService extends IService<BaseInfoEntity> {

    List<BaseInfoEntity> getBaseInfoByIsbn(IsbnInfoEntity isbnInfo);
}
