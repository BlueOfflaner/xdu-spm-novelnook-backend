package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName material
 */
@TableName(value ="material")
@Data
public class MaterialEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long baseInfoId;

    private Long sysInfoId;

    private Long lendInfoId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}