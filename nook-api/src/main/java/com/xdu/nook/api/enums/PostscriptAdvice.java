package com.xdu.nook.api.enums;

public enum PostscriptAdvice {

    /**
     * 待审核
     */
    PENDING("-1"),

    /**
     * 同意
     */
    ACCESS("1"),

    /**
     * 拒绝
     */
    DENIED("0");
    private final String advice;
    PostscriptAdvice(String advice){
        this.advice=advice;
    }

    public String getAdvice() {
        return advice;
    }
}
