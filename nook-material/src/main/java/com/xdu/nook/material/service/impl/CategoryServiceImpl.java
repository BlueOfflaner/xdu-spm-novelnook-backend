package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.CategoryEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.entity.NavigationEntity;
import com.xdu.nook.material.service.CategoryService;
import com.xdu.nook.material.mapper.CategoryMapper;
import com.xdu.nook.material.service.IsbnInfoService;
import com.xdu.nook.material.vo.CategoryListVo;
import com.xdu.nook.material.vo.NavigationListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author 21145
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-04-10 18:42:29
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{

    @Resource
    CategoryService categoryService;

    @Override
    public List<CategoryListVo> getCategoryList() {
        List<CategoryEntity> allList = categoryService.list();
        return bfs(allList);
    }

    @Override
    public List<CategoryEntity> getCategoryListWithBaseInfo(BaseInfoEntity baseInfoEntity) {
        return null;
    }

    private List<CategoryListVo> bfs(List<CategoryEntity> allList) {
        List<List<CategoryListVo>> buckets = new ArrayList<>();
        int maxLevel = allList.stream()
                .map(CategoryEntity::getLevel)
                .reduce(0, (max, level) -> {
                    return level > max ? level : max;
                });

        for (int i = 0; i < maxLevel + 1; i++) {
            buckets.add(new ArrayList<CategoryListVo>());
        }

        allList.forEach(item -> {
            Integer level = item.getLevel();
            CategoryListVo categoryListVo_tmp = new CategoryListVo();
            BeanUtils.copyProperties(item, categoryListVo_tmp);
            buckets.get(level).add(categoryListVo_tmp);
        });

        //挨个处理每个桶和前面的桶
        for (int i = maxLevel; i > 0; i--) {
            List<CategoryListVo> bucket_current = buckets.get(i);
            List<CategoryListVo> bucket_pre = buckets.get(i - 1);
            //遍历每个桶内元素
            for (int j = 0; j < bucket_current.size(); j++) {
                CategoryListVo categoryListVo_current = bucket_current.get(j);
                //遍历上一个桶
                for (int k = 0; k < bucket_pre.size(); k++) {
                    CategoryListVo categoryListVo_parent = bucket_pre.get(k);
                    if (categoryListVo_parent.getId().equals(categoryListVo_current.getPId())) {
                        categoryListVo_parent.getChildren().add(categoryListVo_current);
                        break;
                    }
                }
            }
        }
        return buckets.get(0);
    }
}




