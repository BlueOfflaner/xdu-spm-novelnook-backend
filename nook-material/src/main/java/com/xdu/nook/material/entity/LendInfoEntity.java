package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName lend_info
 */
@TableName(value ="lend_info")
@Data
public class LendInfoEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long materialId;

    private Long recordId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}