package com.xdu.nook.api.utils;

import com.xdu.nook.api.constant.ECode;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * R对象的构造方法，这个方法表明，r对象至少应该包含：
     * <ul>
     *     <li>一个<b><u><i>状态码</i></u></b></li>
     *     <li>一个<b><u><i>信息</i></u></b></li>
     * </ul>
     * 其对象的json表达为：
     *  R={
     *      "code":"001100",
     *      "msg":"这是一个实例信息"
     *  }
     * @author: violet
     */
    public R() {
        put("code", 0);
        put("msg", "success");
    }


    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.put("code",ECode.OK);
        r.put("data", data);
        return r;
    }
    public static R ok(String msg){

        return new R().put("msg",msg).put("code",ECode.OK);
    }
    public static R ok(String msg,Object data) {
        R r = new R();
        r.put("code",ECode.OK);
        r.put("msg", msg);
        r.put("data",data);
        return r;
    }
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.put("code",ECode.OK);
        r.put("msg","");
        r.putAll(map);
        return r;
    }


    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
//    public  Integer getCode() {
//        return (Integer) this.get("code");
//    }

}
