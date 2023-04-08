package com.xdu.nook.material.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName isbn_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private static final long serialVersionUID = 1L;
}