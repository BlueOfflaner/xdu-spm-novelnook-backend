package com.xdu.nook.thirdware.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xdu.nook.thirdware.entity.IsbnInfoEntity;

import java.util.HashMap;
import java.util.Map;

public class ISBNUtils {
    public static IsbnInfoEntity parseEntity(String json_str) {

        //2.获取volumeInfo的json对象
        JSONObject sup_json_obj = JSONObject.parseObject(json_str);
        JSONObject volumeInfo_json_obj = (JSONObject) sup_json_obj.get("volumeInfo");
        //2.1.获取volumeInfo下的title
        String title = (String) volumeInfo_json_obj.get("title");
        //2.2.获取volumeInfo下的subtitle
        String subtitle = (String) volumeInfo_json_obj.get("subtitle");
        //2.3.获取volumeInfo下的所有作者的列表
        JSONArray author_arr = (JSONArray) volumeInfo_json_obj.get("authors");
        //2.4.获取publishedDate 和publisher
        String publishedDate = (String) volumeInfo_json_obj.get("publishedDate");
        String publisher = (String) volumeInfo_json_obj.get("publisher");
        //2.5.获取description
        String description = (String) volumeInfo_json_obj.get("description");
        //2.6.获取isbn码
        JSONArray industryIdentifiers_arr = (JSONArray) volumeInfo_json_obj.get("industryIdentifiers");
        Map<String, String> isbn_info_map = new HashMap<>();
        for (int i = 0; i < industryIdentifiers_arr.size(); i++) {
            JSONObject isbn_info_json_obj = (JSONObject) industryIdentifiers_arr.get(i);
            String type = (String) isbn_info_json_obj.get("type");
            String identifier = (String) isbn_info_json_obj.get("identifier");
            isbn_info_map.put(type, identifier);
        }
        //2.7.获取pageCount
        Integer pageCount = (Integer) volumeInfo_json_obj.get("pageCount");



        IsbnInfoEntity isbn = new IsbnInfoEntity();

        // 1.拼接title串
        String res_title;
        if(null != subtitle){
            res_title = title + ":" + subtitle;
        }else{
            res_title=title;
        }
        isbn.setTitle(res_title);



        StringBuilder sb = new StringBuilder();
        if (null == author_arr) {
            isbn.setAuthor("");
        } else {
            for (int i = 0; i < author_arr.size(); i++) {
                String author_name = (String) author_arr.get(i);
                sb.append(author_name);
                if (i != author_arr.size() - 1) {
                    sb.append(",");
                }
            }
            isbn.setAuthor(sb.toString());
        }

        String res_publish = publisher + ":" + publishedDate;
        isbn.setPublisher(res_publish);
        isbn.setDescription(description);
        isbn.setPages(pageCount);
        String isbn10 = isbn_info_map.get("ISBN_10");
        String isbn13 = isbn_info_map.get("ISBN_13");
        isbn.setIsbn10(isbn10);
        isbn.setIsbn13(isbn13);
        return isbn;
    }

    public static String parseSelfLink(String json_str) throws NullPointerException{
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json_str);
        JSONArray items = (JSONArray) jsonObject.get("items");
        JSONObject o = (JSONObject) items.get(0);
        String selfLink = (String) o.get("selfLink");

        return selfLink;
    }
}
