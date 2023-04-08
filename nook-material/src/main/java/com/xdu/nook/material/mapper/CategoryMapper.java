package com.xdu.nook.material.mapper;

import com.xdu.nook.material.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21145
* @description 针对表【category】的数据库操作Mapper
* @createDate 2023-04-08 20:57:41
* @Entity com.xdu.nook.material.entity.CategoryEntity
*/
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

}




