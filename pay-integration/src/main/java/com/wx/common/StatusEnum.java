package com.wx.common;

public enum StatusEnum {
    SUCCESS("SUCCESS", "美元"),
    CLOSED("CLOSED", "中国人民币"),
    PROCESSING("PROCESSING", "中国人民币"),
    ABNORMAL("ABNORMAL", "中国人民币");

    private String key;
    private String value;

    StatusEnum(String key,String value) {
        this.key = key;
        this.key = key;
    }


    public static void main(String[] args) {
        System.out.println(StatusEnum.SUCCESS);
    }

}
