package com.xdu.nook.material.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.IsbnInfoEntity;
import com.xdu.nook.material.service.IsbnInfoService;
import com.xdu.nook.material.service.IsbnSearchService;
import com.xdu.nook.material.service.MaterialService;
import com.xdu.nook.material.vo.MaterialVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource(name ="isbnSearchServiceImpl")
    IsbnSearchService isbnSearchService;

    @Resource
    IsbnInfoService isbnInfoService;

    @Resource
    MaterialService materialService;

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

    @GetMapping("/get-isbn-info-list")
    public R getIsbnInfoList(@RequestParam(name = "pageSize") Integer pageSize,
                             @RequestParam(name = "currentPage") Integer currentPage) {
        Page<IsbnInfoEntity> pageInfo = new Page<>(currentPage, pageSize);
        isbnInfoService.page(pageInfo);
        return R.ok(pageInfo);
    }

    /*
    @GetMapping("/get-materials-by-isbn")
    public R getMaterialsByIsbn(@RequestParam(name = "isbn") String isbn) {
        IsbnInfoEntity isbnInfo = isbnSearchService.ISBNSearch(isbn);
        if(null == isbnInfo) {
            return R.error(ERCode.SEARCH_ISBN_ERR.getCode(),ERCode.SEARCH_ISBN_ERR.getMsg());
        }

        List<MaterialVo> materialVoList = materialService.getMaterialsByIsbn(isbnInfo);
        return R.ok(materialVoList);
    }
    */
}
