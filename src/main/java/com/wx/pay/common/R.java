package com.wx.pay.common;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
    }

    public static R error() {
        return error(ResultCode.SYSTEM_ERROR.getStatus(),
                ResultCode.SYSTEM_ERROR.getDesc(), null);
    }

    public static R error(String msg) {
        return error(ResultCode.SYSTEM_ERROR.getStatus(), msg, null);
    }


    public static R error(ResultCode code, Object data) {
        return error(code.getStatus(), code.getDesc(), data);
    }

    public static R error(ResultCode code, String msg, Object data) {
        return error(code.getStatus(), msg, data);
    }


    public static R error(int code, String msg, Object data) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    public static R ok(String msg, Object data) {
        R r = new R();
        r.put("code", ResultCode.SUCCESS.getStatus());
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    public static R ok(Integer code, String msg, Object data) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    public static R ok(ResultCode code, Object data) {
        R r = new R();
        r.put("code", code.getStatus());
        r.put("msg", code.getDesc());
        r.put("data", data);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
