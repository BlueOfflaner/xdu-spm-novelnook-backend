package com.xdu.nook.user.controller;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.service.BaseInfoService;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.vo.BaseInfoVo;
import com.xdu.nook.user.vo.ModifyStatusVo;
import com.xdu.nook.user.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/update")
public class UpdateController {

    @Resource
    BaseInfoService baseInfoService;

    @Resource
    SysInfoService sysInfoService;

    @ResponseBody
    @PutMapping("/init-base-info")
    public R initBaseInfo(@RequestBody BaseInfoVo baseInfoVo) {
        baseInfoService.initBaseInfo(baseInfoVo);
        return R.ok();
    }

    @PutMapping("/modify-status")
    public R modifyStatus(@RequestBody ModifyStatusVo modifyStatusVo) {
        //TODO 错误类型
        if(null == modifyStatusVo.getEmail()) {
            return R.error();
        }
        sysInfoService.modifyStatus(modifyStatusVo.getEmail(),
                modifyStatusVo.getPermission(),
                modifyStatusVo.getIsavailable());
        return R.ok();
    }

    @PutMapping("/modify-status-bulk")
    public R modifyStatusBulk(@RequestParam List<UserInfoVo> userInfoVoList) {
        sysInfoService.modifyStatusBulk(userInfoVoList);
        return R.ok();
    }
}
