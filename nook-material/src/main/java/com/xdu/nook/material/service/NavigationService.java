package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.NavigationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xdu.nook.material.vo.NavigationListVo;

import java.util.List;

/**
* @author 21145
* @description 针对表【navigation】的数据库操作Service
* @createDate 2023-04-10 18:42:29
*/
public interface NavigationService extends IService<NavigationEntity> {
    public List<NavigationListVo> getNavigationList();
}
