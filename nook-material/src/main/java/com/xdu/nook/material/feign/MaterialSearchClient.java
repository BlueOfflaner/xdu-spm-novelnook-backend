package com.xdu.nook.material.feign;

import com.xdu.nook.api.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("nook-material")
public interface MaterialSearchClient {

    @GetMapping(value = "/search-isbn")
    public R searchIsbn(String isbn);
}
