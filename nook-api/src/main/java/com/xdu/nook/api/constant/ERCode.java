package com.xdu.nook.api.constant;

public enum ERCode {
    OK("OK","000000"),
    PARAM_ERR("PARAM_ERROR","A10000");
    private String item;
    private String code;

    ERCode(String item,String code) {
        this.code=code;
        this.item=item;

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
