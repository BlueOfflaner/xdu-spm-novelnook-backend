package com.xdu.nook.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_info
 */
@TableName(value ="sys_info")
@Data
public class SysInfo implements Serializable {
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

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}