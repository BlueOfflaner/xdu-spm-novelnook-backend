package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName material
 */
@TableName(value ="material")
@Data
public class MaterialEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long baseInfoId;

    private Long sysInfoId;

    private Long lendInfoId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}