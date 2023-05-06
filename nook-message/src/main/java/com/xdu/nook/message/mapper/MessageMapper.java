package com.xdu.nook.message.mapper;

import com.xdu.nook.message.entity.MessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21145
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-05-05 20:17:50
* @Entity com.xdu.nook.message.entity.MessageEntity
*/

@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {

}




