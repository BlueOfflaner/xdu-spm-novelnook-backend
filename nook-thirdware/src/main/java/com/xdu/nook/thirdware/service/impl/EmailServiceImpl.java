package com.xdu.nook.thirdware.service.impl;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.thirdware.service.EmailService;
import com.xdu.nook.thirdware.utils.SendInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Resource
    JavaMailSender javaMailSender;
    public R sendEmail(SendInfo sendInfo) {
        String from= sendInfo.from;
        String to= sendInfo.to;
        String context = sendInfo.context;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("验证码");
            helper.setText(context, false);
            javaMailSender.send(message);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        R r = R.ok(from+"向"+to+"发送成功");
        return r;
    }

    public R send(SendInfo sendInfo) {
        String from= "2114568838@qq.com";
        String to= sendInfo.to;
        String context = sendInfo.context;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("验证码");
            helper.setText(context, false);
            javaMailSender.send(message);
        }catch (Exception e){
            log.info(e.getMessage());
        }

        R r = R.ok(from+"向"+to+"发送成功");
        return r;
    }
}
