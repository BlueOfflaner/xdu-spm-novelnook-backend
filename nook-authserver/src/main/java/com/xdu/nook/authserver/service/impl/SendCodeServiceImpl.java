package com.xdu.nook.authserver.service.impl;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.authserver.feign.SmsClient;
import com.xdu.nook.authserver.service.CodeFactory;
import com.xdu.nook.authserver.service.SendCodeService;
import com.xdu.nook.authserver.utils.SendInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sendCodeService")
public class SendCodeServiceImpl implements SendCodeService {

    @Resource
    SmsClient smsClient;

    @Resource
    CodeFactory codeFactory;
    public R send(String to) {
        String code = codeFactory.generateCode();

        SendInfo sendInfo=new SendInfo();
        sendInfo.setTo(to);
        String code_msg= "您有来自IsabellaViolet的验证码：\n"+
                code+
                "，\n 有效时间60秒，请注意查收";
        sendInfo.setContext(code_msg);
        smsClient.send(sendInfo);
        return R.ok("发送成功",code);
    }
}
