package com.xdu.nook.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseInfoDto {
    private Long id;
    private String UKIDCode;
    private String name;
    private Date birthday;
    private String email;

}
