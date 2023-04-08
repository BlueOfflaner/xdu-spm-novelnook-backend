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
    VERTIF_CODE_ERR("VERTIF_CODE_ERR","B1001"),

    /**
     * isbn查询失败
     */
    SEARCH_ISBN_ERR("SEARCH_ISBN_ERR","C1001");
    private String msg;
    private String code;

    ERCode(String msg, String code) {
        this.code=code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
