package com.xdu.nook.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName base_info
 */
@TableName(value ="base_info")
@Data
public class BaseInfo implements Serializable {
    private Long id;

    private Long userId;

    private String ukIdCode;

    private String name;

    private Date birthday;

    private static final long serialVersionUID = 1L;
}