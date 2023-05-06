package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @TableName category
 */
@TableName(value ="category")
@Data
public class CategoryEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @JsonProperty("pId")
    private Long pId;

    private Integer level;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}