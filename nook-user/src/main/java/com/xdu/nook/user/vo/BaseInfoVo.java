package com.xdu.nook.user.vo;

import lombok.Data;

@Data
public class BaseInfoVo {
    private Integer id; //userId?,感觉应该是
    private String nickname;
    private String ukid;
    private String birthday;
}
