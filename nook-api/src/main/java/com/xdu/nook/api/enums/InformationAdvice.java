package com.xdu.nook.api.enums;

/**
 * 枚举类，用来列举可能用到的InformationAdvice
 * @author violet
 */
public enum InformationAdvice {


    /**
     * 待处理
     */
    PENDING("Not yet processed","-1"),

    /**
     * 拒绝
     */
    DENIED("Failed","0"),

    /**
     * 接受
     */
    ACCEPT("OK","1");
    private String description;
    private String code;

    InformationAdvice(String description, String code) {
        this.description = description;
        this.code = code;
    }

}

