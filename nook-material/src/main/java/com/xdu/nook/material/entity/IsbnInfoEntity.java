package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName isbn_info
 */
@TableName(value ="isbn_info")
@Data
public class IsbnInfoEntity implements Serializable {
    private Long id;

    @TableField("isbn_10")
    private String isbn10;

    @TableField("isbn_13")
    private String isbn13;

    private String title;

    private String author;

    private String publisher;

    private Integer pages;

    private String image;

    private String description;

    private Integer capacity;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}