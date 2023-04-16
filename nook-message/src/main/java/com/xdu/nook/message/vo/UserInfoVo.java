package com.xdu.nook.message.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserInfoVo  {
    private String UKIDCode;
    private String name;
    private Date birthday;

    //sysInfo部分

    private Long id;

    private Long userId;

    private Integer permission;

    private String email;

    private String password;

    private Integer isAvailable;

    private Integer maxReservationNum;

    private Integer usedReservationNum;

    private Integer maxHoldNum;

    private Integer usedHoldNum;

    private BigDecimal lateFee;


}
