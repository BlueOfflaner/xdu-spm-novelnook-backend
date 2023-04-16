package com.xdu.nook.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @JSONField(serializeUsing = Long.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    @JSONField(serializeUsing = Long.class)
    private Long baseInfoId;

    @JSONField(serializeUsing = Long.class)
    private Long sysInfoId;

    @JSONField(serializeUsing = Long.class)
    private Long borrowInfoId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}