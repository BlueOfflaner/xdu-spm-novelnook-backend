package com.xdu.nook.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseInfoVo {
    private Long id; //userId?,感觉应该是
    private String nickname;
    private String ukid;
    private Date birthday;
}
