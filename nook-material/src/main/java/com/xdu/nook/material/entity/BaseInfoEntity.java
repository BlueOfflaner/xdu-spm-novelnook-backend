package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName base_info
 */
@TableName(value ="base_info")
@Data
public class BaseInfoEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long materialId;

    private Long isbnInfoId;

    private Long localStorage;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}