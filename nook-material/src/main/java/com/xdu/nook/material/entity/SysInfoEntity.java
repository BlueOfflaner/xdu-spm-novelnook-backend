package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_info
 */
@TableName(value ="sys_info")
@Data
public class SysInfoEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer isOccupied;

    private Integer isLimited;

    private Integer isReserved;

    private Long materialId;

    private Integer bookIndex;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}