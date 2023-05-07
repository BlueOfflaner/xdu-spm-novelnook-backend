package com.xdu.nook.message.job.helper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xdu.nook.api.constant.InformationAdvice;
import com.xdu.nook.api.constant.InformationSrc;
import com.xdu.nook.api.constant.InformationType;
import com.xdu.nook.api.entity.Information;
import com.xdu.nook.api.entity.PostScript;
import com.xdu.nook.api.enums.*;
import com.xdu.nook.api.utils.JsonUtils;
import com.xdu.nook.message.component.WebSocketHelper;
import com.xdu.nook.message.entity.MessageEntity;
import com.xdu.nook.message.mq.Producer;
import com.xdu.nook.message.service.MessageService;
import com.xdu.nook.message.utils.JsonHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
public class MessageHelper {
    @Resource
    MessageService messageService;

    @Resource
    WebSocketHelper webSocketHelper;

    @Resource
    Producer producer;

    public void job() {
        //1.查出数据库中所有活跃消息,FCFS策略
        LambdaQueryWrapper<MessageEntity> queryWrap = new LambdaQueryWrapper<>();
        queryWrap.eq(MessageEntity::getIsActive, 1);
        queryWrap.orderByAsc(MessageEntity::getUpdateTime);
        List<MessageEntity> messageList = messageService.list(queryWrap);
        //2.判断当前消息的处理方案，并作出相应处理
        messageList.stream().forEach(message -> {

            PostScript postScript = JsonHelper.getPostScript(message);
            //若当前消息是请求借书，则尝试转发给管理员
            if (postScript.checkType(PostscriptType.CHECKOUT_REQUEST)) {
                System.out.println("已发现活跃消息");
                try {
                    if (postScript.checkAdvice(PostscriptAdvice.PENDING)) {
                        sendAdmin(message);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (postScript.checkType(PostscriptType.CHECKIN)) {
                //TODO checkin相关逻辑
            }
            if (postScript.checkType(PostscriptType.RESPONSE)) {
                //当前消息不再活跃
                try {
                    pushMQ(message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    synchronized private void sendAdmin(MessageEntity message) throws IOException {
        //1.找到合适的在线管理员,随机算法
        HashMap<Long, Session> admin = webSocketHelper.findAdmin();
        System.out.println("已发现在线管理员数量："+admin.size());
        if (admin.size() == 0) return;
        if (message.getAim() != null) {
            HashMap<Long, Session> user = webSocketHelper.findUser(message.getAim());
            System.out.println(user.size());
            if (user.size() > 0) {
                //在线
                String message_json = JsonUtils.objectToJson(message);
                webSocketHelper.sendMessage(user.get(message.getAim()), message_json);
                return;
            }
        }

        Long[] admins = admin.keySet().toArray(new Long[admin.size()]);
        Random random = new Random();
        int i = random.nextInt(admins.length);
        Long selectedAdmin = admins[i];
        Session session = admin.get(selectedAdmin);
        //2.更新当前message信息
        message.setAim(selectedAdmin);

        messageService.updateById(message);
        //3.发送
        String message_json = JsonUtils.objectToJson(message);
        webSocketHelper.sendMessage(session, message_json);
    }



    private void pushMQ(MessageEntity message) {
        //检查当前的回复信息，是否同意
        PostScript postScript = JsonHelper.getPostScript(message);
        boolean flag=false;
        if (postScript.checkAdvice(PostscriptAdvice.ACCESS)) {
            Information information= Information.builder()
                    .callNumber(message.getCallNumber())
                    .userId(message.getAim())
                    .materialId(message.getMaterialId())
                    .type(InformationType.INFORM)
                    .advice(InformationAdvice.CHECKOUT)
                    .src(InformationSrc.MESSAGE)
                    .build();
            producer.sendInformation(JsonUtils.objectToJson(information));
            message.setIsActive(0);
            messageService.updateById(message);
        } else if (postScript.checkAdvice(PostscriptAdvice.DENIED)) {
            try {
                sendUser(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    synchronized private void sendUser(MessageEntity message) throws IOException {

        //尝试发现当前用户是否在线
        HashMap<Long, Session> user = webSocketHelper.findUser(message.getAim());
        //若在线，尝试发送，并将消息置为不活跃
        if (user.size() != 0) {
            webSocketHelper.sendMessage(user.get(message.getAim()), JsonUtils.objectToJson(message));
        }
        //将message做过期处理
        message.setIsActive(0);
        messageService.updateById(message);
    }
}
