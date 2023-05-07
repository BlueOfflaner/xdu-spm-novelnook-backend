package com.xdu.nook.material.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xdu.nook.api.enums.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.CategoryEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.entity.NavigationEntity;
import com.xdu.nook.material.feign.MaterialSearchClient;
import com.xdu.nook.material.service.*;
import com.xdu.nook.material.service.impl.BaseInfoServiceImpl;
import com.xdu.nook.material.vo.CategoryVo;
import com.xdu.nook.material.vo.MaterialInitialVo;
import com.xdu.nook.material.vo.NavigationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Resource
    IsbnInfoService isbnInfoService;

    @Resource
    IsbnSearchService isbnSearchService;

    @Resource
    MaterialSearchClient materialSearchClient;

    @Resource
    MaterialService materialService;

    @Resource
    NavigationService navigationService;

    @Resource
    CategoryService categoryService;

    @Resource
    BaseInfoService baseInfoService;

    @PutMapping("/modify-isbn")
    public R modifyIsbn(@RequestBody IsbnInfoEntity isbnInfo) {
        isbnInfoService.updateById(isbnInfo);
        return R.ok(isbnInfo);
    }

    @PostMapping("/insert-material")
    public R insertMaterial(@RequestBody MaterialInitialVo materialInitialVo) {
        String isbn = materialInitialVo.getIsbnid();
        Long navigationId = materialInitialVo.getNavigation();
        Long selectedMaterialId = materialService.initMaterial(isbn);
        Boolean flag = materialService.updateMaterial(selectedMaterialId, navigationId);
        return (selectedMaterialId == null || !flag) ? R.error(ERCode.INSERT_MATERIAL_ERR) : R.ok(selectedMaterialId);
    }

    @PostMapping("/add-navigation")
    public R addNavigation(@RequestBody NavigationVo navigation) {
        NavigationEntity navigationEntity = new NavigationEntity();
        BeanUtils.copyProperties(navigation, navigationEntity);
        navigationService.save(navigationEntity);
        return R.ok();
    }

    @PutMapping("/update-navigation")
    public R updateNavigation(@RequestBody NavigationVo navigation) {
        NavigationEntity navigationEntity = new NavigationEntity();
        BeanUtils.copyProperties(navigation, navigationEntity);
        navigationService.updateById(navigationEntity);
        return R.ok();
    }

    @DeleteMapping("/delete-navigation")
    public R deleteNavigation(@RequestBody NavigationVo navigation) {
        NavigationEntity navigationEntity = new NavigationEntity();
        BeanUtils.copyProperties(navigation, navigationEntity);

        navigationEntity.setPId(navigation.getPId());
        List<BaseInfoEntity> list = baseInfoService.list(new LambdaQueryWrapper<BaseInfoEntity>()
                .eq(BaseInfoEntity::getLocalStorage
                        , navigationEntity.getId()));

        //若查询不到相关baseInfo,则直接删除
        if (null == list || list.size() == 0) {
            navigationService.removeById(navigationEntity);
            return R.ok();
        }

        List<Long> allRelatedNavigation = list.stream()
                .map(BaseInfoEntity::getLocalStorage)
                .collect(Collectors.toList());


        //将查询到的baseInfo对navigation的绑定清除，再删除
        baseInfoService.update(new LambdaUpdateWrapper<BaseInfoEntity>()
                .in(BaseInfoEntity::getLocalStorage, allRelatedNavigation)
                .set(allRelatedNavigation.size() > 0, BaseInfoEntity::getLocalStorage, null));

        navigationService.removeById(navigationEntity);
        return R.ok();
    }

    @PutMapping("/update-isbn")
    public R updateIsbn(IsbnInfoEntity isbnInfo) {
        isbnInfoService.updateById(isbnInfo);
        return R.ok();
    }

    @PostMapping("/add-category")
    public R addCategory(@RequestBody CategoryVo category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);
        categoryService.save(categoryEntity);
        return R.ok();
    }

    @PutMapping("/update-category")
    public R updateCategory(@RequestBody CategoryVo category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);
        categoryService.updateById(categoryEntity);
        return R.ok();
    }

    @DeleteMapping("/delete-category")
    public R deleteCategory(@RequestBody CategoryVo category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);

        List<IsbnInfoEntity> list = isbnInfoService.list(new LambdaQueryWrapper<IsbnInfoEntity>()
                .eq(IsbnInfoEntity::getCategoryId, categoryEntity.getId()));

        if (null == list || list.size() == 0) {
            categoryService.removeById(categoryEntity);
            return R.ok();
        }

        List<Long> categoryIdList = list.stream()
                .map(IsbnInfoEntity::getCategoryId)
                .collect(Collectors.toList());


        isbnInfoService.update(new LambdaUpdateWrapper<IsbnInfoEntity>()
                .in(IsbnInfoEntity::getCategoryId, categoryIdList)
                .set(categoryIdList.size() > 0, IsbnInfoEntity::getCategoryId, null));

        categoryService.removeById(categoryEntity);

        return R.ok();
    }
}
