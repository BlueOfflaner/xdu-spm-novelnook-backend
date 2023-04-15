package com.xdu.nook.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseInfoDto {
    public Long id;
    public String UKIDCode;
    public String name;
    public Date birthday;
    public String email;

}
