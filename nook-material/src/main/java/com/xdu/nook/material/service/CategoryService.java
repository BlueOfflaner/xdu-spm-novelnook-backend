package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.material.entity.NavigationEntity;
import com.xdu.nook.material.vo.CategoryListVo;
import com.xdu.nook.material.vo.NavigationListVo;

import java.util.List;

/**
* @author 21145
* @description 针对表【category】的数据库操作Service
* @createDate 2023-04-10 18:42:29
*/
public interface CategoryService extends IService<CategoryEntity> {

    public List<CategoryListVo> getCategoryList();

    public List<CategoryEntity> getCategoryListWithBaseInfo(BaseInfoEntity baseInfoEntity);
}
