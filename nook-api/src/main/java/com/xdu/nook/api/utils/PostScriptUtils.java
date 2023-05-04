package com.xdu.nook.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PostScriptUtils {
    private int total = 0;
    private String context = "";

    public void test(){
        JSONObject jsonObject = JSONObject.parseObject(context);
        Object postscript = jsonObject.get("postscript");
        JSONObject note=new JSONObject();
        if(postscript instanceof JSONObject){
            note= (JSONObject) postscript;
        }
        String status = note.getString("status");
        String type = note.getString("type");
        String callnumber = note.getString("callnumber");



    }


}
