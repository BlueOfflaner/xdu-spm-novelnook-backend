package com.xdu.nook.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.message.entity.MessageEntity;
import com.xdu.nook.message.service.MessageService;
import com.xdu.nook.message.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author 21145
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-05-05 20:17:50
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity>
    implements MessageService{

}




