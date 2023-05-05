package com.xdu.nook.material.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xdu.nook.api.enums.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.entity.NavigationEntity;
import com.xdu.nook.material.feign.MaterialSearchClient;
import com.xdu.nook.material.service.*;
import com.xdu.nook.material.vo.MaterialInitialVo;
import com.xdu.nook.material.vo.NavigationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    BaseInfoService baseInfoService;

    @PutMapping("/modify-isbn")
    public R modifyIsbn(@RequestBody IsbnInfoEntity isbnInfo) {
        isbnInfoService.updateById(isbnInfo);
        return R.ok(isbnInfo);
    }

    //TODO 事务控制
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
        list.forEach((item) -> {
            item.setLocalStorage(null);
            baseInfoService.updateById(item);
        });
        navigationService.removeById(navigationEntity);
        return R.ok();
    }

    @PutMapping("/update-isbn")
    public R updateIsbn(IsbnInfoEntity isbnInfo){
        isbnInfoService.updateById(isbnInfo);
        return R.ok();
    }
}
