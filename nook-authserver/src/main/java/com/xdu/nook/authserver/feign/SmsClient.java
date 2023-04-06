package com.xdu.nook.authserver.feign;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.authserver.utils.SendInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("nook-third-ware")
public interface SmsClient {
    @PostMapping("/thirdware/send-sms")
    public R send(@RequestBody SendInfo sendInfo);
}
