package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.*;
import com.xdu.nook.material.service.*;
import com.xdu.nook.material.mapper.MaterialMapper;
import com.xdu.nook.material.vo.MaterialVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 21145
 * @description 针对表【material】的数据库操作Service实现
 * @createDate 2023-04-10 18:42:29
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialEntity>
        implements MaterialService {

    @Resource
    BaseInfoService baseInfoService;

    @Resource
    IsbnInfoService isbnInfoService;

    @Resource
    SysInfoService sysInfoService;

    @Resource
    CategoryService categoryService;

    @Resource
    IsbnSearchService isbnSearchService;

    @Resource
    LendInfoService lendInfoService;

    @Resource
    RecordService recordService;



    public MaterialEntity getMaterialByBaseInfo(BaseInfoEntity baseInfo) {
        if (baseInfo.getMaterialId() == null) {
            return null;
        }
        return this.getById(baseInfo.getMaterialId());
    }

    public Boolean insertMaterial(String isbn_id) {
        IsbnInfoEntity isbnInfoEntity;
        Integer isbn=Integer.parseInt(isbn_id);
        isbnInfoEntity = isbnSearchService.ISBNSearch(isbn);
        if (null == isbnInfoEntity) {
            isbnInfoEntity = isbnSearchService.ISBNOnlineSearch(isbn);
        }
        if (null == isbnInfoEntity) {
            return false;
        }

        Long isbn_info_id = isbnInfoEntity.getId();
        BaseInfoEntity baseInfoEntity = new BaseInfoEntity();
        baseInfoEntity.setIsbnInfoId(isbn_info_id);
        baseInfoService.save(baseInfoEntity);
        Long baseInfoId = baseInfoEntity.getId();

        MaterialEntity materialEntity=new MaterialEntity();
        materialEntity.setBaseInfoId(baseInfoId);
        this.save(materialEntity);
        baseInfoEntity.setMaterialId(materialEntity.getId());
        baseInfoService.updateById(baseInfoEntity);

        SysInfoEntity sysInfoEntity=new SysInfoEntity();

        sysInfoEntity.setMaterialId(materialEntity.getId());
        //TODO 常量
        sysInfoEntity.setIsLimited(0);
        sysInfoEntity.setIsOccupied(0);
        sysInfoEntity.setIsReserved(0);
        sysInfoEntity.setBookIndex(isbnInfoEntity.getCapacity()+1);
        sysInfoService.save(sysInfoEntity);

        materialEntity.setSysInfoId(sysInfoEntity.getId());
        this.updateById(materialEntity);

        return false;
    }

    /*
    private void packMaterialVo(MaterialVo materialVo, MaterialEntity material, List<List<CategoryEntity>> categoryList) {
        if (material == null) {
            return;
        }

        BaseInfoEntity baseInfo = baseInfoService.getById(material.getBaseInfoId());
        IsbnInfoEntity isbnInfo = isbnInfoService.getById(baseInfo.getIsbnInfoId());
        SysInfoEntity sysInfo = sysInfoService.getById(material.getSysInfoId());

        //List<CategoryEntity> categoryTopList = categoryList.get(0);
        //List<CategoryEntity> categoryMidList = categoryList.get(1);
        //List<CategoryEntity> categoryLowList = categoryList.get(2);

        List<CategoryEntity> tmpList = new ArrayList<>();

        if (null != sysInfo || null != sysInfo.get()) {
            int cnt = 2;
            Long id = sysInfo.getCategoryId();
            while (cnt >= 0) {
                Long finalId = id;
                CategoryEntity categoryEntity = categoryList.get(cnt).stream().filter(s -> s.getId().equals(finalId)).findAny().get();
                tmpList.add(categoryEntity);
                id = categoryEntity.getPId();
                cnt--;
            }
        }

        LendInfoEntity lendInfo = lendInfoService.getById(material.getLendInfoId());
        RecordEntity record = recordService.getById(lendInfo.getRecordId());

        if (null == lendInfo) {
            lendInfo = new LendInfoEntity();
            record = new RecordEntity();
        }

        BeanUtils.copyProperties(baseInfo, materialVo);
        BeanUtils.copyProperties(isbnInfo, materialVo);
        BeanUtils.copyProperties(sysInfo, materialVo);
        materialVo.setCategoryEntityList(tmpList);
        BeanUtils.copyProperties(lendInfo, materialVo);
        BeanUtils.copyProperties(record, materialVo);
    }
    public List<MaterialVo> getMaterialsByIsbn(IsbnInfoEntity isbnInfo) {
        List<BaseInfoEntity> baseInfoList = baseInfoService.getBaseInfoByIsbn(isbnInfo);
        List<MaterialVo> materialVoList = new ArrayList<>();

        List<List<CategoryEntity>> categoryList = new ArrayList<>();
        categoryList.add(categoryService.getCategoryTopList());
        categoryList.add(categoryService.getCategoryMidList());
        categoryList.add(categoryService.getCategoryLowList());

        baseInfoList.forEach(baseInfoEntity -> {
            MaterialEntity material = this.getMaterialByBaseInfo(baseInfoEntity);
            MaterialVo materialVo = new MaterialVo();
            packMaterialVo(materialVo, material, categoryList);
            materialVoList.add(materialVo);
        });
        return materialVoList;
    }
    */
}




