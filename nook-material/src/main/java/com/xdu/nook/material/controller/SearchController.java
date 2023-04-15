package com.xdu.nook.material.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdu.nook.api.constant.ERCode;
import com.xdu.nook.api.utils.R;
import com.xdu.nook.material.entity.*;
import com.xdu.nook.material.service.*;
import com.xdu.nook.material.vo.NavigationListVo;
import com.xdu.nook.material.vo.SearchMaterialVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource(name = "isbnSearchServiceImpl")
    IsbnSearchService isbnSearchService;

    @Resource
    IsbnInfoService isbnInfoService;

    @Resource
    MaterialService materialService;

    @Resource
    SysInfoService sysInfoService;

    @Resource
    NavigationService navigationService;

    @Resource
    BaseInfoService baseInfoService;

    @Resource
    CategoryService categoryService;

    /**
     * 处理传入的isbn码，若本地已经存在，则返回本地结果，若本地尚未存在则上网搜索，并且持久化到本地，同样返回查找到的结果
     *
     * @param isbn
     * @return
     */

    @GetMapping("/search-isbn")
    public R searchIsbn(String isbn) {
        //TODO 处理初始化isbn的问题
        IsbnInfoEntity localSearchedRes = isbnSearchService.ISBNSearch(isbn);
        if (null == localSearchedRes) {
            IsbnInfoEntity onlineSearchedRes = isbnSearchService.ISBNOnlineSearch(isbn);

            if (null == onlineSearchedRes) {
                return R.error(ERCode.SEARCH_ISBN_ERR.getCode(), ERCode.SEARCH_ISBN_ERR.getMsg());
            } else {
                isbnInfoService.save(onlineSearchedRes);
                return R.ok(onlineSearchedRes);
            }
        } else {
            return R.ok(localSearchedRes);
        }
    }

    @GetMapping("/get-isbn-info-page")
    public R getIsbnInfoList(@RequestParam(name = "pageSize") Integer pageSize,
                             @RequestParam(name = "currentPage") Integer currentPage) {
        Page<IsbnInfoEntity> pageInfo = new Page<>(currentPage, pageSize);
        isbnInfoService.page(pageInfo);
        return R.ok(pageInfo);
    }


    @GetMapping("/get-navigation-all")
    public R getNavigationList() {
        List<NavigationListVo> navigationList = navigationService.getNavigationList();
        if (navigationList == null) {
            return R.error(ERCode.SEARCH_NAVIGATION_ERR);
        }
        return R.ok(navigationList);
    }

    @GetMapping("/get-all-material-with-isbn")
    public R getMaterialWithIsbn(String isbn) {
        Long isbnId = isbnSearchService.ISBNCheck(isbn);
        if (isbnId == null) {
            return R.error(ERCode.SEARCH_ISBN_ERR);
        }
        List<SearchMaterialVo> searchMaterialVoL = new ArrayList<>();
        SearchMaterialVo searchMaterialVo = new SearchMaterialVo();

        List<BaseInfoEntity> baseInfoList = baseInfoService.list(new LambdaQueryWrapper<BaseInfoEntity>()
                .eq(BaseInfoEntity::getIsbnInfoId,
                        isbnId));
        baseInfoList.stream().forEach(item -> {
            SysInfoEntity sysInfo = sysInfoService.getSysInfoWithBaseInfo(item);
            searchMaterialVo.setSysInfo(sysInfo);
            IsbnInfoEntity isbnInfo = isbnInfoService.getIsbnWithBaseInfoService(item);
            searchMaterialVo.setIsbnInfo(isbnInfo);
            List<NavigationEntity> navigationList = navigationService.getNavigationListWithBaseInfo(item);
            searchMaterialVo.setNavigationL(navigationList);
            List<CategoryEntity> categoryList = categoryService.getCategoryListWithBaseInfo(item);
            searchMaterialVo.setCategoryL(categoryList);
            searchMaterialVoL.add(searchMaterialVo);
        });
        return R.ok(searchMaterialVoL);
    }
}
