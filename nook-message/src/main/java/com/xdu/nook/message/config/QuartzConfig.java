package com.xdu.nook.message.config;

import com.xdu.nook.message.job.JobDemo;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail quartzJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class)
                .storeDurably()
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger quartzTrigger() {
        ScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0/2 * * * * ?");


        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(scheduleBuilder)
                .build();
        return trigger;
    }
}
