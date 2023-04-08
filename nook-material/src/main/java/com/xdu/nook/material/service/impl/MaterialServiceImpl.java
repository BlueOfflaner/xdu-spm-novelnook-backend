package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.MaterialEntity;
import com.xdu.nook.material.service.MaterialService;
import com.xdu.nook.material.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

/**
* @author 21145
* @description 针对表【material】的数据库操作Service实现
* @createDate 2023-04-08 20:57:41
*/
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialEntity>
    implements MaterialService{

}




