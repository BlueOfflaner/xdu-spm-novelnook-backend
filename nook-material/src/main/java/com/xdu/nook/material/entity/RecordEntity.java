package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName record
 */
@TableName(value ="record")
@Data
public class RecordEntity implements Serializable {
    private Long id;

    private Long lendInfoId;

    private Long materialId;

    private Date beginTime;

    private Date dueTime;

    private Date returnTime;

    private Long borrowerId;

    private String callNumber;

    private Integer isActive;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}