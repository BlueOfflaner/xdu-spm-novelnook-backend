package com.xdu.nook.message.test.comprehensive;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xdu.nook.message.feign.UserClient;
import com.xdu.nook.message.job.JobDemo;
import com.xdu.nook.message.mq.Reminder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

    @Resource
    UserClient userClient;

    @Resource
    Reminder reminder;

    @Test
    public void run() {
        test();
    }

    public void test() {
        String jsonString = userClient.getUserDetailedInfo();
        JSONArray userDetailedInfo = JSONArray.parseArray(jsonString);
        int size = userDetailedInfo.size();
        if (size >= 0) {
            for (Object o : userDetailedInfo) {
                if (o instanceof JSONObject) {
                    JSONObject json_item = (JSONObject) o;

                    Integer usedHoldNum = (Integer) json_item.get("usedHoldNum");

                    Object userId_obj = json_item.get("userId");
                    Long userId;
                    if (userId_obj instanceof Integer) {
                        userId = ((Integer) userId_obj).longValue();
                    } else if (userId_obj instanceof Long) {
                        userId = ((Long) userId_obj);
                    } else {
                        throw new RuntimeException("寄");
                    }

                    if (usedHoldNum > 0) {
                        check(userId, usedHoldNum);
                    }
                }
            }
        }
    }

    /**
     * 处理具体借书的人，并尝试处理其对应记录表
     *
     * @param userId
     * @param holdNumber
     */
    public void check(Long userId, Integer holdNumber) {
        String msg = userId + "你已经欠费";
        reminder.arrearsRemind(msg);
    }
}
