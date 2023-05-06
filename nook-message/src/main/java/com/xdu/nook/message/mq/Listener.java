package com.xdu.nook.message.mq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xdu.nook.api.constant.InformationType;
import com.xdu.nook.api.entity.Information;
import com.xdu.nook.api.entity.PostScript;
import com.xdu.nook.api.enums.PostscriptAdvice;
import com.xdu.nook.api.utils.JsonUtils;
import com.xdu.nook.message.entity.MessageEntity;
import com.xdu.nook.message.service.MessageService;
import com.xdu.nook.message.utils.JsonHelper;
import net.minidev.json.JSONUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RocketMQMessageListener(consumerGroup = "messageGroup", topic = "message")
public class Listener implements RocketMQListener<String> {

    @Resource
    MessageService messageService;

    public static class AcknowledgeInfo {
        Integer ackTimes;
        Integer ackSignal;
    }

    public static final Map<String, AcknowledgeInfo> acknowledgeMap = new ConcurrentHashMap<>();

    @Override
    public void onMessage(String msg) {
        //累计确认算法
        Information information = JsonUtils.jsonToObject(msg, Information.class);
        ack(information);
    }

    public void clean(String callNumber) {
        acknowledgeMap.remove(callNumber);
    }

    public void ack(Information information) {
        String callNumber = information.getCallNumber();

        if (acknowledgeMap.containsKey(callNumber)) {
            //若当前信息已经存在，则继续确认

            //获取当前业务的确认信息
            AcknowledgeInfo acknowledgeInfo = acknowledgeMap.get(callNumber);

            if (acknowledgeInfo.ackTimes == 1) {
                //若当前information已完成一次确认，只要再次确认即可
                if (information.getType().equals(InformationType.ACKNOWLEDGE)) {
                    acknowledgeInfo.ackSignal++;
                }
                acknowledgeInfo.ackTimes++;

                //若有任何一次拒绝，则拒绝本项
                if (acknowledgeInfo.ackSignal < 2) {
                    information.setType(InformationType.DENIED);
                }
                acknowledgeMap.remove(callNumber);
                response(information);
            }
        } else {
            //若当前项尚未存在，则准备确认
            AcknowledgeInfo acknowledgeInfo = new AcknowledgeInfo();
            acknowledgeInfo.ackTimes = 1;
            acknowledgeInfo.ackSignal = 0;
            if (information.getType().equals(InformationType.ACKNOWLEDGE)) {
                acknowledgeInfo.ackSignal++;
            }
            acknowledgeMap.put(callNumber, acknowledgeInfo);
        }
    }

    public void response(Information information) {
        String advice = information.getAdvice();
        String src = information.getSrc();
        String callNumber = information.getCallNumber();
        String type = information.getType();

        LambdaQueryWrapper<MessageEntity> queryWrap = new LambdaQueryWrapper<>();
        queryWrap.eq(MessageEntity::getCallNumber, callNumber)
                .orderByDesc(MessageEntity::getUpdateTime)
                .last("limit 1");

        MessageEntity response_message = messageService.getOne(queryWrap);

        PostScript postScript = JsonHelper.getPostScript(response_message);

        if (advice.equals(InformationType.DENIED)) {
            postScript.setAdvice(PostscriptAdvice.DENIED.getAdvice());
            String s = JsonUtils.objectToJson(postScript);
            response_message.setPostscript(s);

        }
        //活化
        response_message.setIsActive(1);
        messageService.updateById(response_message);
    }
}
