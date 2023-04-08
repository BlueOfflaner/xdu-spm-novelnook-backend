package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName isbn_info
 */
@TableName(value ="isbn_info")
@Data
public class IsbnInfoEntity implements Serializable {
    @TableId(type = IdType.AUTO)
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

    private static final long serialVersionUID = 1L;
}