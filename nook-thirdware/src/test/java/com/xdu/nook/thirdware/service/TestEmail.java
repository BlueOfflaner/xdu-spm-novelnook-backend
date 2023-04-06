package com.xdu.nook.thirdware.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEmail {
    @Resource
    JavaMailSender javaMailSender;
    @Test
    public void test() throws MessagingException {
        String from="2114568838@qq.com";
        String to="1765017394@qq.com";
        String context ="测试数据";

        MimeMessage message =javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("验证码");
        helper.setText(context,false);
        javaMailSender.send(message);
    }
}
