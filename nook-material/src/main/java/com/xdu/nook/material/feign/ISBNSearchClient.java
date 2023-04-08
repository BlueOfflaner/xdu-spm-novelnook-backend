package com.xdu.nook.material.feign;

import com.xdu.nook.material.entity.IsbnInfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("nook-third-ware")
public interface ISBNSearchClient {
    @GetMapping(value = "/search-isbn")
    public IsbnInfoEntity searchIsbn(@RequestParam(value="isbn") String isbn);
}
