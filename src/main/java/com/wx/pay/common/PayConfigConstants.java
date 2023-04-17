package com.wx.pay.common;

public class PayConfigConstants {

    /**证书路径**/
    public static String cert = "";
    public static String privateKey ="xx";
    /**商户号**/
    public static String mchId = "164170912";
    /**序列号**/
    public static String mchSerialno ="670CFBD729DE9AC29CA51886E11F0B2A0956B69D";
    /**APIV3秘钥**/
    public static String apiV3Key ="15344FCC931F49FA86012CCE42711159";
    /**微信下单**/
    public static String createOrder ="https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out-trade-no}/close";
    /**查询订单**/
    public static String queryOrder ="https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction-id}";
    /**关闭订单**/
    public static String closeOrder ="https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out-trade-no}/close";
    /**申请退款**/
    public static String refundOrder ="https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
    /**查询单笔退款**/
    public static String refundQuery ="https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out-refund-no}";
    /**申请交易账单**/
    public static String applyTradebill ="https://api.mch.weixin.qq.com/v3/bill/tradebill";

}
