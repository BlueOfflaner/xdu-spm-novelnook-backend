package com.xdu.nook.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdu.nook.user.entity.BorrowInfo;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 21145
* @description 针对表【borrow_info】的数据库操作Mapper
* @createDate 2023-04-06 21:51:11
* @Entity com.xdu.nook.user.entity.BorrowInfo
*/
@Mapper
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {


}
