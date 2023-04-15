package com.xdu.nook.material.vo;

import com.xdu.nook.material.entity.NavigationEntity;

import java.util.ArrayList;
import java.util.List;



public class NavigationListVo extends NavigationEntity {
    List<NavigationListVo> children ;

    public NavigationListVo() {
        this.children=new ArrayList<>();
    }

    public NavigationListVo(List<NavigationListVo> children) {
        this.children = children;
    }


    public List<NavigationListVo> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationListVo> children) {
        this.children = children;
    }

    public List<NavigationListVo> bfs() {
        return null;
    }
}
