package com.xdu.nook.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.user.dto.RegistDto;
import com.xdu.nook.user.dto.UserBaseInfoDto;
import com.xdu.nook.user.entity.BaseInfo;
import com.xdu.nook.user.entity.SysInfo;
import com.xdu.nook.user.entity.User;
import com.xdu.nook.user.service.BaseInfoService;
import com.xdu.nook.user.service.SysInfoService;
import com.xdu.nook.user.service.UserService;
import com.xdu.nook.user.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
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

    @Resource
    BaseInfoService baseInfoService;


    @PostMapping("/welcome")
    public UserBaseInfoDto welcomeUser(@RequestBody RegistDto registDto) {
        UserBaseInfoDto userBaseInfoDto = userService.welcomeUser(registDto.getEmail(), registDto.getPassword());
        return userBaseInfoDto;
    }


    @GetMapping("/get-user-info")
    public R getOneUser(@RequestParam String id) {
        UserInfoVo userInfoVo = userService.getOneUser(Long.valueOf(id));
        if (null == userInfoVo) {
            return R.error();
        }
        return R.ok(userInfoVo);
    }

    @GetMapping("/get-user-info-list")
    public R getUserInfoList(@RequestParam(name = "pagesize") Integer pageSize,
                             @RequestParam(name = "currentpage") Integer currentPage) {
        Page page = userService.getUserInfoList(pageSize, currentPage);
        return R.ok(page);
    }

    @GetMapping("/get-user-info-all")
    public R getUserInfoAll() {
        List<UserInfoVo> userInfoVoList = userService.getUserInfoAll();
        return R.ok(userInfoVoList);
    }

    @GetMapping("/get-user-detailed-info-all")
    public String getUserDetailedInfo() {

        List<UserInfoVo> userInfoAll = userService.getUserInfoAll();
        JSONArray jsonArray = new JSONArray();
        userInfoAll.stream().forEach(item -> {
            JSONObject jsonObject_item = new JSONObject();
            jsonObject_item.put("name", item.getName());
            jsonObject_item.put("userId", item.getUserId());
            jsonObject_item.put("usedHoldNum", item.getUsedHoldNum());
            jsonArray.add(jsonObject_item);
        });
        return jsonArray.toJSONString();
    }



    @PostMapping("/password/login")
    public UserBaseInfoDto loginWithPassword(@RequestParam String email
            , @RequestParam String password) {
        SysInfo selectedSys = sysInfoService.getOne(new LambdaQueryWrapper<SysInfo>()
                .eq(SysInfo::getEmail, email)
                .eq(SysInfo::getPassword, password));

        if (selectedSys == null)
            return null;
        Long userId = selectedSys.getUserId();
        UserBaseInfoDto userBaseInfoDto = new UserBaseInfoDto();
        BaseInfo baseInfoByUserId = baseInfoService.getBaseInfoByUserId(userId);
        BeanUtils.copyProperties(baseInfoByUserId, userBaseInfoDto);
        userBaseInfoDto.setUKIDCode(baseInfoByUserId.getUKIDCode());
        return userBaseInfoDto;
    }


    @GetMapping("/check-is-admin")
    public boolean checkIsAdmin(Long userId){
        User user = userService.getById(userId);
        if(user==null)return false;
        SysInfo sysInfoByUserId = sysInfoService.getSysInfoByUserId(userId);
        return sysInfoByUserId.getPermission()<=2;
    }
}
