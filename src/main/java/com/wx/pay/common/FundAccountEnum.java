package com.wx.pay.common;

/**
 * @author jiang漫天
 */

public enum FundAccountEnum {

    UNSETTLED("UNSETTLED", "可用余额"),
    AVAILABLE("AVAILABLE", "不可用余额"),
    UNAVAILABLE("UNAVAILABLE", "不可用余额"),
    OPERATION("OPERATION", "不可用余额"),
    BASIC("BASIC", "不可用余额");

    private String key;
    private String value;

    FundAccountEnum(String key, String value) {
        this.key = key;
        this.key = key;
    }


    public static void main(String[] args) {
        System.out.println(FundAccountEnum.AVAILABLE);
    }


}
