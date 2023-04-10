package com.xdu.nook.material.controller;

import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.feign.MaterialSearchClient;
import com.xdu.nook.material.service.IsbnInfoService;
import com.xdu.nook.material.service.IsbnSearchService;
import com.xdu.nook.material.service.MaterialService;
import com.xdu.nook.material.vo.MaterialVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Resource
    IsbnInfoService isbnInfoService;

    @Resource
    IsbnSearchService isbnSearchService;

    @Resource
    MaterialSearchClient materialSearchClient;

    @Resource
    MaterialService materialService;

    @PutMapping("/modify-isbn")
    public R modifyIsbn(@RequestBody IsbnInfoEntity isbnInfo) {
        IsbnInfoEntity localSearchedRes = isbnSearchService.ISBNSearch(isbnInfo.getIsbn10());
        if(null == localSearchedRes) {
            localSearchedRes = isbnSearchService.ISBNSearch(isbnInfo.getIsbn13());
        }
        if(null == localSearchedRes) {
            return R.error(ERCode.SEARCH_ISBN_ERR.getCode(),ERCode.SEARCH_ISBN_ERR.getMsg());
        }
        isbnInfoService.updateById(localSearchedRes);
        return R.ok(localSearchedRes);
    }

    //TODO 事务控制
    @PostMapping("/insert-material")
    public R insertMaterial(@RequestBody String isbnid) {
        boolean flag = materialService.insertMaterial(isbnid);
        return flag==true? R.ok():R.error();
    }
}
