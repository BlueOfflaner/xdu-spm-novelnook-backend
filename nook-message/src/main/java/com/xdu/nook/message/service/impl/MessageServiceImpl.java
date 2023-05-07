package com.xdu.nook.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.api.constant.InformationSrc;
import com.xdu.nook.api.entity.Information;
import com.xdu.nook.message.entity.MessageEntity;
import com.xdu.nook.message.service.MessageService;
import com.xdu.nook.message.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 21145
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-05-05 20:17:50
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity>
    implements MessageService{

    @Resource
    MessageService messageService;
    @Override
    public void persist(MessageEntity msg) {

        messageService.save(msg);
    }

    /**
     * {
     *     "userId":"1145141919810",
     *     "materialId":"124545452121",
     *     "callNumber":"1234541231",
     *     "type":"acknowledge/inform",
     *     "advice":"-1/0/1",
     *     "src":"message/material/user"
     * }
     * @param message
     * @return
     */
    @Override
    public Information generateInformation(MessageEntity message) {
        Information information=Information.builder()
                .userId(message.getSrc())
                .materialId(message.getMaterialId())
                .callNumber(message.getCallNumber())
                .src(InformationSrc.MESSAGE)
                .build();
        return information;
    }
}




