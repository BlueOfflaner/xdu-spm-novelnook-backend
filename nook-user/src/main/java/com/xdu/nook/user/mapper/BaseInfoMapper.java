package com.xdu.nook.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdu.nook.user.entity.BaseInfo;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 21145
* @description 针对表【base_info】的数据库操作Mapper
* @createDate 2023-04-06 21:51:11
* @Entity com.xdu.nook.user.entity.BaseInfo
*/
@Mapper
public interface BaseInfoMapper extends BaseMapper<BaseInfo> {


}
