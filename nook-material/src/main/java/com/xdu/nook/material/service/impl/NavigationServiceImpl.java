package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.material.entity.BaseInfoEntity;
import com.xdu.nook.material.entity.NavigationEntity;
import com.xdu.nook.material.service.NavigationService;
import com.xdu.nook.material.mapper.NavigationMapper;
import com.xdu.nook.material.vo.NavigationListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 21145
 * @description 针对表【navigation】的数据库操作Service实现
 * @createDate 2023-04-10 18:42:29
 */
@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, NavigationEntity>
        implements NavigationService {

    @Resource
    NavigationService navigationService;



    @Override
    public List<NavigationListVo> getNavigationList() {
        List<NavigationEntity> allList = navigationService.list();
        return bfs(allList);
    }

    @Override
    public List<NavigationEntity> getNavigationListWithBaseInfo(BaseInfoEntity baseInfoEntity) {
        List<NavigationEntity> nList= new ArrayList<>();
        Long localStorageId = baseInfoEntity.getLocalStorage();
        NavigationEntity last = navigationService.getById(localStorageId);
        nList.add(last);
        Integer level = last.getLevel();
        Long selectedId =last.getPId();
        for(int i=0;i<level;i++){
            NavigationEntity byId = navigationService.getById(selectedId);
            nList.add(byId);
            selectedId=byId.getPId();
        }
        return nList;
    }


    private List<NavigationListVo> bfs(List<NavigationEntity> allList) {
        //TODO 要点1
        int maxLevel = allList.stream()
                .map(NavigationEntity::getLevel)
                .reduce(0, (max, level) -> {
                    return level > max ? level : max;
                });
        List<List<NavigationListVo>> buckets = new ArrayList<>();

        //TODO 要点2
        for (int i = 0; i < maxLevel + 1; i++) {
            buckets.add(new ArrayList<NavigationListVo>());
        }
        //TODO 要点3
        allList.forEach(item -> {
            Integer level = item.getLevel();
            NavigationListVo navigationListVo_tmp = new NavigationListVo();
            BeanUtils.copyProperties(item, navigationListVo_tmp);
            buckets.get(level).add(navigationListVo_tmp);
        });
        //TODO 要点4
        //挨个处理每个桶和前面的桶
        for (int i = maxLevel; i > 0; i--) {
            List<NavigationListVo> bucket_current = buckets.get(i);
            List<NavigationListVo> bucket_pre = buckets.get(i - 1);
            //遍历每个桶内元素
            for (int j = 0; j < bucket_current.size(); j++) {
                NavigationListVo navigationListVo_current = bucket_current.get(j);
                //遍历上一个桶
                for (int k = 0; k < bucket_pre.size(); k++) {
                    NavigationListVo navigationListVo_parent = bucket_pre.get(k);
                    if (navigationListVo_parent.getId().equals(navigationListVo_current.getPId())) {
                        navigationListVo_parent.getChildren().add(navigationListVo_current);
                        break;
                    }
                }
            }
        }

        return buckets.get(0);
    }
}




