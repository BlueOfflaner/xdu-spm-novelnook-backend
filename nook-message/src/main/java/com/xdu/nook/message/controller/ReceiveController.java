package com.xdu.nook.message.controller;

import com.xdu.nook.api.utils.CallNumberUtils;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.message.entity.MessageEntity;
import com.xdu.nook.message.mq.Producer;
import com.xdu.nook.message.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ReceiveController {

    @Resource
    MessageService messageService;

    @Resource
    Producer producer;

    /**
     * 接收消息并做出一系列处理
     * @param message
     * @return
     */
    @PostMapping("/adapter")
    R receive(@RequestBody MessageEntity message) {
        try {
            //生成流水号
            String callNumber = CallNumberUtils.getCallNumber();
            if(message.getCallNumber()!=null)
            message.setCallNumber(callNumber);
            message.setIsActive(1);
            //将message持久化到数据库
            messageService.persist(message);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}
