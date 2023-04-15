package com.xdu.nook.material.vo;

import com.xdu.nook.material.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchMaterialVo {
    private IsbnInfoEntity isbnInfo;
    private List<NavigationEntity> navigationL;

    private SysInfoEntity sysInfo;

    private List<CategoryEntity> categoryL;
}
