package com.wx.pay.common;

public enum CurrencyEnum {
    USD("USD", "美元"),
    CNY("CNY", "中国人民币");

    private String key;
    private String value;
    // 构造方法
    CurrencyEnum(String key,String value) {
        this.key = key;
        this.key = key;
    }


    public static void main(String[] args) {
        System.out.println(CurrencyEnum.CNY);
    }


}
