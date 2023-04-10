package com.xdu.nook.material.entity;

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
public class BaseInfoEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long materialId;

    private Long isbnInfoId;

    private Long localStorage;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}