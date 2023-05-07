package com.xdu.nook.api.enums;

public enum PostscriptType {
    CHECKOUT_REQUEST("checkout request"),

    CHECKIN("checkin"),

    RESPONSE("response");

    private String type;

    PostscriptType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
