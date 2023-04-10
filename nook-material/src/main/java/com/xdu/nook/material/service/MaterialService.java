package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.entity.MaterialEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.material.vo.MaterialVo;

import java.util.List;

/**
* @author 21145
* @description 针对表【material】的数据库操作Service
* @createDate 2023-04-08 20:57:41
*/
public interface MaterialService extends IService<MaterialEntity> {

    List<MaterialVo> getMaterialsByIsbn(IsbnInfoEntity isbnInfo);

    MaterialEntity getMaterialByBaseInfo(BaseInfoEntity baseInfo);

    void insertMaterial(MaterialVo materialVo, IsbnInfoEntity isbnInfo);
}
