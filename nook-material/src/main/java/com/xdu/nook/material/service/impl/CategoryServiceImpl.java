package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.CategoryEntity;
import com.xdu.nook.material.service.CategoryService;
import com.xdu.nook.material.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21145
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-04-08 20:57:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{

    public List<CategoryEntity> getCategoryTopList() {
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryEntity::getLevel, 1);
        List<CategoryEntity> list = this.list(queryWrapper);
        return list;
    }

    public List<CategoryEntity> getCategoryMidList() {
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryEntity::getLevel, 2);
        List<CategoryEntity> list = this.list(queryWrapper);
        return list;
    }

    public List<CategoryEntity> getCategoryLowList() {
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryEntity::getLevel, 3);
        List<CategoryEntity> list = this.list(queryWrapper);
        return list;
    }
}




