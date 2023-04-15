package com.xdu.nook.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.dto.UserBaseInfoDto;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.service.UserService;
import com.xdu.nook.user.vo.UserInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Resource
    UserService userService;

    @Resource
    SysInfoService sysInfoService;

    @ResponseBody
    @PostMapping("/welcome/")
    public UserBaseInfoDto welcomeUser(@RequestParam String email,@RequestParam String password){
        UserBaseInfoDto userBaseInfoDto = userService.welcomeUser(email,password);
        return userBaseInfoDto;
    }

    @ResponseBody
    @GetMapping("/get-user-info")
    public R getOneUser(@RequestParam String id) {
        UserInfoVo userInfoVo = userService.getOneUser(Long.valueOf(id));
        if(null == userInfoVo) {
            return R.error();
        }
        return R.ok(userInfoVo);
    }

    @GetMapping("/get-user-info-list")
    public R getUserInfoList(@RequestParam(name = "pagesize") Integer pageSize,
                             @RequestParam(name = "currentpage") Integer currentPage) {
        Page page = userService.getUserInfoList(pageSize, currentPage);
        return R.ok(page);
        //page.getRecords().forEach(System.out::println);
    }

    @GetMapping("/get-user-info-all")
    public R getUserInfoAll() {
        List<UserInfoVo> userInfoVoList = userService.getUserInfoAll();
        return R.ok(userInfoVoList);
    }

    @ResponseBody
    @PostMapping("/password/login")
    public UserBaseInfoDto loginWithPassword(@RequestParam String email
            ,@RequestParam String password){
        SysInfo selectedSys = sysInfoService.getOne(new LambdaQueryWrapper<SysInfo>()
                .eq(SysInfo::getEmail, email)
                .eq(SysInfo::getPassword, password));

        Long userId = selectedSys.getUserId();
        UserBaseInfoDto userBaseInfoDto = new UserBaseInfoDto();
        userBaseInfoDto.setId(userId);
        return  userBaseInfoDto;
    }
}
