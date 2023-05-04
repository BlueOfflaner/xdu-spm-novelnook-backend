package com.xdu.nook.message.controller;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.message.entity.MessageEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveController {

    @PostMapping("/send-message")
    public R sendMessage(@RequestBody MessageEntity message){
        Long aim = message.getAim();
        Long src = message.getSrc();
        Long materialId = message.getMaterialId();
        String postscript = message.getPostscript();
        return R.error();
    }
}
