package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.IsbnInfoEntity;

public interface IsbnSearchService {

    public IsbnInfoEntity ISBNOnlineSearch(String isbn);

    public IsbnInfoEntity ISBNSearch(String isbn);

    public Long ISBNCheck(String isbn);
}
