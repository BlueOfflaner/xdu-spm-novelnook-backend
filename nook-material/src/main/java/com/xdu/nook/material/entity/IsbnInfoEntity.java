package com.xdu.nook.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private String isbn10;

    private String isbn13;

    private String title;

    private String author;

    private String publisher;

    private Integer pages;

    private String image;

    private String description;

    private Integer capacity;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}