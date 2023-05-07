package com.xdu.nook.message.utils;

import com.xdu.nook.api.entity.Information;
import com.xdu.nook.api.entity.PostScript;
import com.xdu.nook.api.utils.JsonUtils;
import com.xdu.nook.message.entity.MessageEntity;

public class JsonHelper {

    /**
     * 提取message对象中的postscript(Json字串)，并返回PostScript对象
     *
     * @param message message对象
     */
    public static PostScript getPostScript(MessageEntity message) {
        String postScriptJson = message.getPostscript();
        return JsonUtils.jsonToObject(postScriptJson, PostScript.class);
    }

    /**
     * 将json字串转换为Information对象
     *
     * @param informationJson json字串
     */
    public static Information getInformation(String informationJson) {
        return JsonUtils.jsonToObject(informationJson, Information.class);
    }




}
