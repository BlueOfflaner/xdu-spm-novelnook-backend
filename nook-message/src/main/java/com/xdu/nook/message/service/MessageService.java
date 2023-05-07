package com.xdu.nook.message.service;

import com.xdu.nook.api.entity.Information;
import com.xdu.nook.message.entity.MessageEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 21145
* @description 针对表【message】的数据库操作Service
* @createDate 2023-05-05 20:17:50
*/
public interface MessageService extends IService<MessageEntity> {

    void persist(MessageEntity msg);

    Information generateInformation(MessageEntity message);
}
