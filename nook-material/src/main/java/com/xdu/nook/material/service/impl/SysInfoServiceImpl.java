package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.MaterialEntity;
import com.xdu.nook.material.entity.SysInfoEntity;
import com.xdu.nook.material.service.BaseInfoService;
import com.xdu.nook.material.service.MaterialService;
import com.xdu.nook.material.service.SysInfoService;
import com.xdu.nook.material.mapper.SysInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 21145
* @description 针对表【sys_info】的数据库操作Service实现
* @createDate 2023-04-10 18:42:29
*/
@Service
public class SysInfoServiceImpl extends ServiceImpl<SysInfoMapper, SysInfoEntity>
    implements SysInfoService{

    @Resource
    MaterialService materialService;

    @Resource
    BaseInfoService baseInfoService;
    @Override
    public SysInfoEntity getSysInfoWithBaseInfo(BaseInfoEntity baseInfoEntity) {

        MaterialEntity material = materialService.getById(baseInfoEntity.getMaterialId());
        SysInfoEntity selectedSysInfo = this.getById(material.getSysInfoId());
        return selectedSysInfo;
    }
}




