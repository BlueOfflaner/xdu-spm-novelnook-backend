package com.xdu.nook.thirdware.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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