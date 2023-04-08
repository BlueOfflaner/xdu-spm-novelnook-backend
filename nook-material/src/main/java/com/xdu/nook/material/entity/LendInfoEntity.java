package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName lend_info
 */
@TableName(value ="lend_info")
@Data
public class LendInfoEntity implements Serializable {
    private Long id;

    private Long materialId;

    private Long records;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}