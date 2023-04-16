package com.xdu.nook.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(serializeUsing = Long.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    @JSONField(serializeUsing = Long.class)
    private Long borrowInfoId;

    @JSONField(serializeUsing = Long.class)
    private Long userId;

    private String callNumber;

    private Date beginTime;

    private Date dueTime;

    private Date returnTime;

    private Integer isActive;

    private static final long serialVersionUID = 1L;
}