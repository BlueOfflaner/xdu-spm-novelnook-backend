package com.xdu.nook.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xdu.nook.api.entity.Information;
import com.xdu.nook.api.entity.PostScript;
import com.xdu.nook.api.enums.InformationSrc;
import com.xdu.nook.api.enums.InformationType;

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
        try {
            obj = JSON.parseObject(json, clazz);
        } catch (JSONException e) {
            throw e;
        }
        return obj;
    }

    /**
     * 将json字串转换为JSONObject
     *
     * @param json json字串
     */
    public static JSONObject jsonToJSONObject(String json) {
        try {
            return JSON.parseObject(json);
        } catch (JSONException e) {
            System.err.println("Failed to convert json to JSONObject: " + e.getMessage());
            return null;
        }
    }


}
