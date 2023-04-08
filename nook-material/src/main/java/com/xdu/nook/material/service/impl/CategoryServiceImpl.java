package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.CategoryEntity;
import com.xdu.nook.material.service.CategoryService;
import com.xdu.nook.material.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 21145
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-04-08 20:57:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{

}




