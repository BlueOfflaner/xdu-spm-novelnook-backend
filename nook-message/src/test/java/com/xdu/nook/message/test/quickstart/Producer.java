package com.xdu.nook.message.test.quickstart;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("test-group");
            producer.setNamesrvAddr("192.168.163.130:9876");
            producer.start();
            for (int i = 0; i < 3; i++) {
                Message msg = new Message("Topic-test",
                        "Tag-test",
                        ("hello,rocketmq" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            }
            producer.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
