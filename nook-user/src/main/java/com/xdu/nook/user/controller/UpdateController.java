package com.xdu.nook.user.controller;

import com.xdu.nook.api.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/update")
public class UpdateController {


    @ResponseBody
    @PostMapping("/init-base-info")
    public R initBaseInfo(){
        return R.error();
    }
}
