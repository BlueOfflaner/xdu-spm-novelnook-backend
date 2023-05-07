package com.xdu.nook.message.feign;

import com.alibaba.fastjson.JSONArray;
import com.xdu.nook.api.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@FeignClient("nook-user")
public interface UserClient {
    @GetMapping("/agent/get-user-detailed-info-all")
    public String getUserDetailedInfo();

    @GetMapping("/agent/check-is-admin")
    public boolean checkIsAdmin(@RequestParam("userId") Long userId);
}
