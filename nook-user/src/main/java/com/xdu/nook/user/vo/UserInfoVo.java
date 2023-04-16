package com.xdu.nook.user.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.xdu.nook.user.entity.SysInfo;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
public class UserInfoVo extends SysInfo {
    private String UKIDCode;
    private String name;
    private Date birthday;
    @JSONField(serializeUsing = Long.class)
    private Long userId;
}
