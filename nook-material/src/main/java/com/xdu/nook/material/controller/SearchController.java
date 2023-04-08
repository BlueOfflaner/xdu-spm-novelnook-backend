package com.xdu.nook.material.controller;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.feign.ISBNSearchClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    ISBNSearchClient isbnSearchClient;

    @GetMapping("/search-isbn")
    public R searchIsbn(String isbn){
        IsbnInfoEntity isbnInfoEntity = isbnSearchClient.searchIsbn(isbn);
        System.out.println(isbnInfoEntity);
        if(null == isbnInfoEntity){
            return R.error(ERCode.SEARCH_ISBN_ERR.getCode(),ERCode.SEARCH_ISBN_ERR.getMsg());
        }else {
            return R.ok(isbnInfoEntity);
        }
    }
}
