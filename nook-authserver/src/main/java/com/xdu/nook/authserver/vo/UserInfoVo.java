package com.xdu.nook.authserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    public Long id;

    public String token;

    public String UKIDCode;

    public String name;
    public Date birthday;

}
