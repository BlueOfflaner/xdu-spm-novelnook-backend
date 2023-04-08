package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName category
 */
@TableName(value ="category")
@Data
public class CategoryEntity implements Serializable {
    private Long id;

    private String name;

    private Long pId;

    private Integer level;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}