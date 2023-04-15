package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @TableName navigation
 */
@TableName(value ="navigation")
@Data
public class NavigationEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer level;

    @JsonProperty("pId")
    private Long pId;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}