package com.wx.common;

/**
 * @author jiang漫天
 */

public enum BillRecordEnum {

    TRANSACTIONBILL("资金账单", "资金账单申请"),
    FUNDBILL("交易账单", "交易账单申请");

    private String key;
    private String value;

    BillRecordEnum(String key,String value) {
        this.key = key;
        this.value = value;
    }


    public static void main(String[] args) {
        System.out.println(BillRecordEnum.FUNDBILL.key);
    }
}
