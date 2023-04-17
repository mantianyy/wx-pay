package com.wx.pay.common;

public enum AccountEnum {

    AVAILABLE("AVAILABLE", "可用余额"),
    UNAVAILABLE("UNAVAILABLE", "不可用余额");

    private String key;
    private String value;
    // 构造方法
    AccountEnum(String key,String value) {
        this.key = key;
        this.key = key;
    }


    public static void main(String[] args) {
        System.out.println(AccountEnum.AVAILABLE);
    }

}
