package com.xdu.nook.user.entity;

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
    private Long id;

    private Long userId;

    private Long recordId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}