package com.xdu.nook.thirdware.controller;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.thirdware.service.EmailService;
import com.xdu.nook.thirdware.utils.SendInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/thirdware")
public class EmailController {

    @Resource
    private EmailService emailService;
    @PostMapping("/send-email")
    public R sendEmail(@RequestBody SendInfo sendInfo){
        R r = emailService.sendEmail(sendInfo);
        return r;
    }

    @PostMapping("/send-sms")
    public R send(@RequestBody SendInfo sendInfo){
        R send = emailService.send(sendInfo);
        return send;
    }

}
