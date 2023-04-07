package com.xdu.nook.user.vo;

import com.xdu.nook.user.entity.SysInfo;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo extends SysInfo {
    private String UKIDCode;
    private String name;
    private Date birthday;
}
