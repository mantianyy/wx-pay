package com.wx.pay.common;

public enum ResultCode {
    /*成功状态码:0*/
    SUCCESS(0, "成功!"),
    /*失败状态码:1*/
    ERROR(1, "失败!"),
    /*参数错误:1001-1004*/
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    /*系统异常:1005*/
    SYSTEM_ERROR(1005, "系统异常,请联系管理员"),
    /*用户操作:2001-2009*/
    USER_NOT_LOGIN_IN(2001, "用户未登录,需登录验证"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_NOT_ACCOUNT_FORBIDDEN_IN(2003, "账号已禁用"),
    USER_NOT_EXIST_IN(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户不存在"),
    USER_UPLOAD_SUCCESS(2006, "上传成功!"),
    USER_UPLOAD_FAILED(2007, "上传失败!"),
    USER_DOWNLOAD_SUCCESS(2008,"下载成功!"),
    USER_DOWNLOAD_FAILED(2009,"下载失败!"),
    WORKFLOW_SUCCESS(200,"审批成功!"),
    PAY_NOTIFY_SUCCESS(000,"SUCCESS"),
    PAY_NOTIFY_FAIL(111,"FAIL");


    private int status;
    private String desc;

    ResultCode(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }


}
