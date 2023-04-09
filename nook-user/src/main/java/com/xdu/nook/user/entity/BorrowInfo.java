package com.xdu.nook.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}