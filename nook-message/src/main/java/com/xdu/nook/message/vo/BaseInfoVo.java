package com.xdu.nook.message.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseInfoVo {
    private Long id;
    private String email;
    private String nickname;
    private String ukid;
    private Date birthday;
}
