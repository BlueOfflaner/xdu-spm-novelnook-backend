package com.xdu.nook.thirdware.controller;

import com.xdu.nook.thirdware.entity.IsbnInfoEntity;
import com.xdu.nook.thirdware.utils.ISBNUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class ISBNController {

    public static final String base_url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";


    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "/search-isbn")
    public IsbnInfoEntity searchIsbn(@RequestParam("isbn") String isbn) {
        try {
            String url = base_url + isbn;

            String forObject = restTemplate.getForObject(url, String.class);
            String selfLink = ISBNUtils.parseSelfLink(forObject);
            String self_json = restTemplate.getForObject(selfLink, String.class);
            IsbnInfoEntity isbnInfoEntity = ISBNUtils.parseEntity(self_json);
            System.out.println(isbnInfoEntity);
            return isbnInfoEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
