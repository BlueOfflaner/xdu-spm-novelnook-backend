package com.xdu.nook.material.vo;

import com.xdu.nook.material.entity.NavigationEntity;
import org.reflections.Reflections;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface BreadthFirstSearchable {
    public default List<BreadthFirstSearchable> bfs(List<BreadthFirstSearchable> allList){

        int maxLevel = allList.stream()
                .map(BreadthFirstSearchable::getLevel)
                .reduce(0, (max, level) -> {
                    return level > max ? level : max;
                });
        List<List<BreadthFirstSearchable>> buckets = new ArrayList<>();


        for (int i = 0; i < maxLevel + 1; i++) {
            buckets.add(new ArrayList<BreadthFirstSearchable>());
        }

        allList.forEach(item -> {
            Integer level = item.getLevel();


            Reflections reflections=new Reflections("com.xdu.nook.material.vo");
            Set<Class<? extends BreadthFirstSearchable>> subClass = reflections.getSubTypesOf(BreadthFirstSearchable.class);
            subClass.forEach((sub)->{

            });


            BreadthFirstSearchable breadthFirstSearchable ;
            //BeanUtils.copyProperties(item, breadthFirstSearchable);
            //buckets.get(level).add(breadthFirstSearchable);
        });

        //挨个处理每个桶和前面的桶
        for (int i = maxLevel; i > 0; i--) {
            List<BreadthFirstSearchable> bucket_current = buckets.get(i);
            List<BreadthFirstSearchable> bucket_pre = buckets.get(i - 1);
            //遍历每个桶内元素
            for (int j = 0; j < bucket_current.size(); j++) {
                BreadthFirstSearchable bfsAble_current = bucket_current.get(j);
                //遍历上一个桶
                for (int k = 0; k < bucket_pre.size(); k++) {
                    BreadthFirstSearchable bfsAble_parent = bucket_pre.get(k);
                    if (bfsAble_parent.getId().equals(bfsAble_current.getPId())) {
                        bfsAble_parent.getChildren().add(bfsAble_current);
                        break;
                    }
                }
            }
        }

        return buckets.get(0);
    }

    public List<BreadthFirstSearchable> getChildren();

    public void setChildren(List<BreadthFirstSearchable> children) ;

    public Integer getLevel();

    public Long getPId();

    public Long getId();
}
