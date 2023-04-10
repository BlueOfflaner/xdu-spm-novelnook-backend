package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21145
* @description 针对表【category】的数据库操作Service
* @createDate 2023-04-08 20:57:41
*/
public interface CategoryService extends IService<CategoryEntity> {

    List<CategoryEntity> getCategoryTopList();

    List<CategoryEntity> getCategoryMidList();

    List<CategoryEntity> getCategoryLowList();
}
