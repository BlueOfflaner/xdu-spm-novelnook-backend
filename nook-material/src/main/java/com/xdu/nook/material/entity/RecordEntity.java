package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName record
 */
@TableName(value ="record")
@Data
public class RecordEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long lendInfoId;

    private Long materialId;

    private Date beginTime;

    private Date dueTime;

    private Date returnTime;

    private Long borrowerId;

    private String callNumber;

    private Integer isActive;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}