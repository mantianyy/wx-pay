package com.wx.pay.common;

public enum ChannelEnum {
    ORIGINAL("ORIGINAL", "原路退款"),
    BALANCE("BALANCE", "退回到余额"),
    OTHER_BALANCE("OTHER_BALANCE","原账户异常退到其他余额账户"),
    OTHER_BANKCARD("OTHER_BANKCARD","原银行卡异常退到其他银行卡");

    private String key;
    private String value;

    ChannelEnum(String key,String value) {
        this.key = key;
        this.key = key;
    }


    public static void main(String[] args) {
        System.out.println(ChannelEnum.ORIGINAL);
    }

}
