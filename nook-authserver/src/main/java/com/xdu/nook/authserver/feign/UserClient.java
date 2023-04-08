package com.xdu.nook.authserver.feign;

import com.xdu.nook.authserver.dto.UserBaseInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("nook-user")
public interface UserClient {
    @GetMapping("welcome/{email}")
    public UserBaseInfoDto welcomeUser(@PathVariable(value = "email") String email);
}
