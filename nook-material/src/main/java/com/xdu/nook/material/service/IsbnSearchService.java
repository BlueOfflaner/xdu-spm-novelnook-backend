package com.xdu.nook.material.service;

import com.xdu.nook.material.entity.IsbnInfoEntity;

public interface IsbnSearchService {

    IsbnInfoEntity ISBNOnlineSearch(String isbn);

    IsbnInfoEntity ISBNSearch(String isbn);
}
