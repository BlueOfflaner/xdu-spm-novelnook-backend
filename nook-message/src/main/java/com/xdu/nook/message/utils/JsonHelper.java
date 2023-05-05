package com.xdu.nook.message.utils;

import com.xdu.nook.api.entity.Information;
import com.xdu.nook.api.entity.PostScript;
import com.xdu.nook.api.enums.InformationSrc;
import com.xdu.nook.api.enums.InformationType;
import com.xdu.nook.message.entity.MessageEntity;

import javax.validation.constraints.NotNull;

import static com.xdu.nook.api.utils.JsonUtils.jsonToObject;
import static com.xdu.nook.api.utils.JsonUtils.objectToJson;

public class JsonHelper {

    /**
     * 提取message对象中的postscript(Json字串)，并返回PostScript对象
     *
     * @param message message对象
     */
    public static PostScript getPostScript(MessageEntity message) {
        String postScriptJson = message.getPostscript();
        return jsonToObject(postScriptJson, PostScript.class);
    }

    /**
     * 将json字串转换为Information对象
     *
     * @param informationJson json字串
     */
    public static Information getInformation(String informationJson) {
        return jsonToObject(informationJson, Information.class);
    }

    /**
     * 组装informationJson
     */
    public static String makeInformationJson(@NotNull MessageEntity message, InformationType type,
                                             InformationSrc src, Integer advice) {
        Long userId = message.getSrc();
        Long materialId = message.getMaterialId();
        String callNumber = message.getCallNumber();
        Information information = Information.builder()
                .userId(userId)
                .materialId(materialId)
                .callNumber(callNumber)
                .type(type)
                .advice(advice)
                .src(src)
                .build();
        return objectToJson(information);
    }


}
