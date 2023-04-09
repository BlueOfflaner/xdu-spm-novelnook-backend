package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName navigation
 */
@TableName(value ="navigation")
@Data
public class NavigationEntity implements Serializable {
    private Long id;

    private Integer level;

    private Long pId;

    private String name;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}