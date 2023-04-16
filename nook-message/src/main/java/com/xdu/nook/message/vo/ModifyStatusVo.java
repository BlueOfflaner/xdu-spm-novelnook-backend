package com.xdu.nook.message.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyStatusVo {
    private String email;
    private Integer permission;
    private Integer isavailable;
    private String token;
}
