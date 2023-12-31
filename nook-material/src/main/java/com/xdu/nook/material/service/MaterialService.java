package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.MaterialEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 21145
* @description 针对表【material】的数据库操作Service
* @createDate 2023-04-10 22:54:16
*/
public interface MaterialService extends IService<MaterialEntity> {
    public Long initMaterial(String isbn);

    public Boolean updateMaterial(Long materialId, Long navigationId);

}
