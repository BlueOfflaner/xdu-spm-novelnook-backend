package com.xdu.nook.material.controller;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.service.IsbnInfoService;
import com.xdu.nook.material.service.IsbnSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource(name ="isbnSearchServiceImpl")
    IsbnSearchService isbnSearchService;

    @Resource
    IsbnInfoService isbnInfoService;

    /**
     * 处理传入的isbn码，若本地已经存在，则返回本地结果，若本地尚未存在则上网搜索，并且持久化到本地，同样返回查找到的结果
     * @param isbn
     * @return
     */
    //TODO 事务处理
    @GetMapping("/search-isbn")
    public R searchIsbn(String isbn){
        IsbnInfoEntity localSearchedRes = isbnSearchService.ISBNSearch(isbn);
        if(null == localSearchedRes){
            IsbnInfoEntity onlineSearchedRes = isbnSearchService.ISBNOnlineSearch(isbn);

            if(null == onlineSearchedRes){
                return R.error(ERCode.SEARCH_ISBN_ERR.getCode(),ERCode.SEARCH_ISBN_ERR.getMsg());
            }else {
                isbnInfoService.save(onlineSearchedRes);
                return R.ok(onlineSearchedRes);
            }
        }else{
            return R.ok(localSearchedRes);
        }
    }
}
