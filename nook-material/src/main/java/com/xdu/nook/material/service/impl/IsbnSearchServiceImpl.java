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

    @Override
    public IsbnInfoEntity ISBNOnlineSearch(String isbn){
        IsbnInfoEntity isbnInfoEntity = isbnSearchClient.searchIsbn(isbn);
        System.out.println(isbnInfoEntity);
        /*
        if(null == isbnInfoEntity){
            return R.error(ERCode.SEARCH_ISBN_ERR.getCode(),ERCode.SEARCH_ISBN_ERR.getMsg());
        }else {
            return R.ok(isbnInfoEntity);
        }
        */
        return isbnInfoEntity;
    }

    @Override
    public IsbnInfoEntity ISBNSearch(String isbn){
        LambdaQueryWrapper<IsbnInfoEntity> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(null!=isbn,IsbnInfoEntity::getIsbn13,isbn)
                .or(queryWrapperBefore->{
            queryWrapperBefore.eq(null!=isbn,IsbnInfoEntity::getIsbn10,isbn);
        });

        IsbnInfoEntity selectedIsbn = isbnInfoService.getOne(queryWrapper);

        return selectedIsbn;
    }
}
