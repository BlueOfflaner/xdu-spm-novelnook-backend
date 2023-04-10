package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_info
 */
@TableName(value ="sys_info")
@Data
public class SysInfoEntity implements Serializable {
    private Long id;

    private Integer isOccupied;

    private Integer isLimited;

    private Integer isReserved;

    private Integer bookIndex;

    private Long categoryId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}