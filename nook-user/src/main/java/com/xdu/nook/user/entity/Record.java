package com.xdu.nook.user.entity;

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
public class Record implements Serializable {
    private Long id;

    private Long borrowInfoId;

    private Long userId;

    private String callNumber;

    private Date beginTime;

    private Date dueTime;

    private Date returnTime;

    private Integer isActive;

    private static final long serialVersionUID = 1L;
}