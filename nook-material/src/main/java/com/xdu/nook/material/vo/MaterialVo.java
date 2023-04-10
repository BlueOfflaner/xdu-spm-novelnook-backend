package com.xdu.nook.material.vo;

import com.xdu.nook.material.entity.CategoryEntity;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MaterialVo extends IsbnInfoEntity {
    //BaseInfo
    private String storageLocation;



    private Date initTime;

    //SysInfo
    private Integer isOccupied;

    private Integer isLimited;

    private Integer isReserved;

    private Integer bookIndex;

    private Long categoryId;

    //Category
    List<CategoryEntity> categoryEntityList;

    //LendInfo->Record
    private Date beginTime;

    private Date dueTime;

    private Date returnTime;

    private Long borrowerId;

    private String callNumber;

    private Integer isActive;
}
