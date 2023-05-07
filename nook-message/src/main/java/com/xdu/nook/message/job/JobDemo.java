package com.xdu.nook.message.job;


import com.xdu.nook.message.feign.UserClient;
import com.xdu.nook.message.job.helper.MessageHelper;
import com.xdu.nook.message.mq.Producer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

public class JobDemo extends QuartzJobBean {
    @Resource
    UserClient userClient;

    @Resource
    Producer producer;

    @Resource
    MessageHelper messageHelper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        messageHelper.job();
    }


}
