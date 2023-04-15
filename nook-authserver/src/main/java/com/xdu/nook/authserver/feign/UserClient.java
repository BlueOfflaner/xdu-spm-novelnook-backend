package com.xdu.nook.authserver.feign;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.authserver.dto.UserBaseInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("nook-user")
public interface UserClient {
    @PostMapping("/agent/welcome")
    public UserBaseInfoDto welcomeUser(@RequestParam("email") String email
            , @RequestParam("password") String password);

    @ResponseBody
    @PostMapping("/agent/password/login")
    public UserBaseInfoDto loginWithPassword(@RequestParam("email") String email
            ,@RequestParam("password") String password);
}
