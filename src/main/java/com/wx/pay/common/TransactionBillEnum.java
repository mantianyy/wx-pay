package com.wx.pay.common;

public enum TransactionBillEnum {

    SUCCESS("SUCCESS", "交易成功"),
    REFUND("REFUND", "已退款");

    private String key;
    private String value;
    TransactionBillEnum(String key,String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getKey(String code){
        for(TransactionBillEnum transactionBillEnum : TransactionBillEnum.values()){
            if(transactionBillEnum.getKey().equals(code)){
                return transactionBillEnum.value;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(TransactionBillEnum.SUCCESS);
    }
}
