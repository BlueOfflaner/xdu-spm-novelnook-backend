package com.xdu.nook.message.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName message
 */
@TableName(value ="message")
@Data
public class MessageEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long src;

    private Long aim;

    private Long materialId;

    private String postscript;

    private Integer isActive;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}