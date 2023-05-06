package com.xdu.nook.material.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xdu.nook.material.entity.CategoryEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CategoryListVo {
    private Long id;

    private String name;

    private Long pId;

    private Integer level;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public List<CategoryListVo> children;

    public CategoryListVo() {
        this.children = new ArrayList<>();
    }

    public CategoryListVo(CategoryEntity categoryEntity) {
        BeanUtils.copyProperties(categoryEntity, this);
        this.children = new ArrayList<>();
    }


    public List<CategoryListVo> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryListVo> children) {
        this.children = children;
    }

    public List<CategoryListVo> bfs() {
        return null;
    }
}
