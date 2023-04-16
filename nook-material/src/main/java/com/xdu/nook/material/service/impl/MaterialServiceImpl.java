package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.api.constant.MaterialConstant;
import com.xdu.nook.material.entity.*;
import com.xdu.nook.material.service.*;
import com.xdu.nook.material.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author violet
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

    @Resource
    NavigationService navigationService;


    public Long initMaterial(String isbn) {
        IsbnInfoEntity isbnInfoEntity;
        if(isbn.length()==0)return null;
        //1.分析传入参数
        //1.1.尝试认为传入参数为isbn识别码
        //1.1.1.尝试本地查找isbn记录
        isbnInfoEntity = isbnSearchService.ISBNSearch(isbn);
        //1.1.2.若本地查找失败，则网络搜索
        if (null == isbnInfoEntity) {
            isbnInfoEntity = isbnSearchService.ISBNOnlineSearch(isbn);
        }
        //1.2.若都失败，则尝试认为isbn为对象id，尝试根据id查询
        if (null == isbnInfoEntity) {
            isbnInfoEntity = isbnInfoService.getById(Long.parseLong(isbn));
        }
        //1.3.若仍然失败
        if (null == isbnInfoEntity) {
            return null;
        }


        Long isbn_info_id = isbnInfoEntity.getId();
        BaseInfoEntity baseInfoEntity = new BaseInfoEntity();
        baseInfoEntity.setIsbnInfoId(isbn_info_id);
        baseInfoService.save(baseInfoEntity);

        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setBaseInfoId(baseInfoEntity.getId());
        this.save(materialEntity);
        baseInfoEntity.setMaterialId(materialEntity.getId());
        baseInfoService.updateById(baseInfoEntity);

        SysInfoEntity sysInfoEntity = new SysInfoEntity();

        sysInfoEntity.setMaterialId(materialEntity.getId());

        sysInfoEntity.setIsLimited(MaterialConstant.DEFAULT_LIMITED_STATUS);
        sysInfoEntity.setIsOccupied(MaterialConstant.DEFAULT_OCCUPIED_STATUS);
        sysInfoEntity.setIsReserved(MaterialConstant.DEFAULT_RESERVED_STATUS);
        sysInfoEntity.setBookIndex(isbnInfoEntity.getCapacity() + 1);
        sysInfoService.save(sysInfoEntity);

        LendInfoEntity lendInfo = new LendInfoEntity();
        lendInfo.setMaterialId(materialEntity.getId());
        lendInfoService.save(lendInfo);

        materialEntity.setLendInfoId(lendInfo.getId());
        materialEntity.setSysInfoId(sysInfoEntity.getId());

        this.updateById(materialEntity);

        return materialEntity.getId();
    }


    public Boolean updateMaterial(Long materialId, Long navigationId) {
        MaterialEntity material = this.getById(materialId);
        if(null ==material)return false;
        Long baseInfoId = material.getBaseInfoId();
        BaseInfoEntity baseInfo = baseInfoService.getById(baseInfoId);
        if(baseInfo ==null)return false;
        baseInfo.setLocalStorage(navigationId);
        baseInfoService.updateById(baseInfo);

        IsbnInfoEntity isbnWithBaseInfoService = isbnInfoService.getIsbnWithBaseInfoService(baseInfo);
        isbnWithBaseInfoService.setCapacity(isbnWithBaseInfoService.getCapacity()+1);
        isbnInfoService.updateById(isbnWithBaseInfoService);
        return true;
    }


}




