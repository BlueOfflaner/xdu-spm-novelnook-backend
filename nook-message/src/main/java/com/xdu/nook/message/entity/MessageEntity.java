package com.xdu.nook.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName message
 */
@TableName(value ="message")
@Data
public class MessageEntity implements Serializable {
    private Long id;

    private Long src;

    private Long aim;

    private Long materialId;

    private String postscript;

    private Integer isActive;

    private String callNumber;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}