package com.wx.common;

public enum SignTypeEnum {

    DOWNLOAD_URL_SIGN(0, "下载地址签名"),
    PREPAY_PAY_SIGN(1, "预支付交易签名");

    private int key;
    private String value;

    SignTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }


    public static void main(String[] args) {

        System.out.println(SignTypeEnum.DOWNLOAD_URL_SIGN);
    }
}
