package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.CategoryEntity;
import com.xdu.nook.material.service.CategoryService;
import com.xdu.nook.material.mapper.CategoryMapper;
import com.xdu.nook.material.vo.CategoryListVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21145
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-04-10 18:42:29
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{

    //TODO 待实现
    @Override
    public List<CategoryListVo> getCategoryList() {
        return null;
    }

    //TODO 待实现
    @Override
    public List<CategoryEntity> getCategoryListWithBaseInfo(BaseInfoEntity baseInfoEntity) {
        return null;
    }
}




