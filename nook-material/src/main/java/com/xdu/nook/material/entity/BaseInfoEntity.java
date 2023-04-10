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
    private Long id;

    private Long mId;

    private Long isbnInfoId;

    private String storageLocation;

    private Date initTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}