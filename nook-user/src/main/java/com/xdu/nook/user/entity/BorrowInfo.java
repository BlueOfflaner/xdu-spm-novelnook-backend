package com.xdu.nook.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName borrow_info
 */
@TableName(value ="borrow_info")
@Data
public class BorrowInfo implements Serializable {

    @JSONField(serializeUsing = Long.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    @JSONField(serializeUsing = Long.class)
    private Long userId;

    @JSONField(serializeUsing = Long.class)
    private Long recordId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}