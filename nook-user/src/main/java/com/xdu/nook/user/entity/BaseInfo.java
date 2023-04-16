package com.xdu.nook.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName base_info
 */
@TableName(value ="base_info")
@Data
public class BaseInfo implements Serializable {

    @JSONField(serializeUsing = Long.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    @JSONField(serializeUsing = Long.class)
    private Long userId;

    private String UKIDCode;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}