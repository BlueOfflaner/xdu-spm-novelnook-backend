package com.xdu.nook.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.feign.ISBNSearchClient;
import com.xdu.nook.material.service.IsbnSearchService;
import com.xdu.nook.material.service.IsbnInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IsbnSearchServiceImpl implements IsbnSearchService {

    @Resource
    ISBNSearchClient isbnSearchClient;

    @Resource
    IsbnInfoService isbnInfoService;


    public IsbnInfoEntity ISBNOnlineSearch(String isbn) {
        IsbnInfoEntity isbnInfoEntity = isbnSearchClient.searchIsbn(isbn);
        return isbnInfoEntity;
    }


    public IsbnInfoEntity ISBNSearch(String isbn) {
        LambdaQueryWrapper<IsbnInfoEntity> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(null != isbn, IsbnInfoEntity::getIsbn13, isbn)
                .or(queryWrapperBefore -> {
                    queryWrapperBefore.eq(null != isbn, IsbnInfoEntity::getIsbn10, isbn);
                });

        IsbnInfoEntity selectedIsbn = isbnInfoService.getOne(queryWrapper);

        return selectedIsbn;
    }


    public Long ISBNCheck(String isbn) {
        IsbnInfoEntity isbnInfoEntity;
        if(isbn.length()==0)return null;
        //1.分析传入参数
        //1.1.尝试认为传入参数为isbn识别码
        //1.1.1.尝试本地查找isbn记录
        isbnInfoEntity = this.ISBNSearch(isbn);
        //1.1.2.若本地查找失败，则网络搜索
        if (null == isbnInfoEntity) {
            isbnInfoEntity = this.ISBNOnlineSearch(isbn);
        }
        //1.2.若都失败，则尝试认为isbn为对象id，尝试根据id查询
        if (null == isbnInfoEntity) {
            isbnInfoEntity = isbnInfoService.getById(Long.parseLong(isbn));
        }
        //1.3.若仍然失败
        if (null == isbnInfoEntity) {
            return null;
        }
        return isbnInfoEntity.getId();
    }


}
