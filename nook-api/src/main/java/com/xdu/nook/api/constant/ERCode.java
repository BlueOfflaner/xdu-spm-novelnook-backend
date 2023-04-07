package com.xdu.nook.api.constant;

public enum ERCode {
    /**
     * 一切正常
     */
    OK("OK","00000"),
    /**
     * 请求参数错误
     */
    PARAM_ERR("PARAM_ERROR","A1000"),

    /**
     * 验证码错误
     */
    VERTIF_CODE_ERR("VERTIF_CODE_ERR","A1001");
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
