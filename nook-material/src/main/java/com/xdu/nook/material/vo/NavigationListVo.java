package com.xdu.nook.material.vo;

import com.xdu.nook.material.entity.NavigationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavigationListVo extends NavigationEntity {
    List<NavigationListVo> children =new ArrayList<>();

}
