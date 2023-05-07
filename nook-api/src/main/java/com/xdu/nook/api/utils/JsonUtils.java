package com.xdu.nook.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    /**
     * 将对象转换为json字串
     *
     * @param obj 待转换对象
     */
    public static String objectToJson(Object obj) throws JSONException {
        String json = null;
        try {
            json = JSON.toJSONString(obj);
        } catch (JSONException e) {
            throw e;
        }
        return json;
    }

    /**
     * 将json字串转换为T类型对象
     *
     * @param json  json字串
     * @param clazz 对象类型, 如MyEntity.class
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) throws JSONException {
        T obj = null;
        //TODO 此处有可改进之处
        try {
            obj = JSON.parseObject(json, clazz);
        } catch (JSONException e) {
            throw e;
        }
        return obj;
    }



}
