package com.wx.dto.query;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询单笔退款订单接口参数返回模型")
public class QuerySingleRefundResDto {
    @ApiModelProperty("微信支付退款单号")
    @JSONField(name = "refund_id")
    private String refundId;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("退款入账账户")
    @JSONField(name = "user_received_account")
    private String userReceivedAccount;
    @ApiModelProperty("退款成功时间")
    @JSONField(name = "success_time")
    private String successTime;
    @ApiModelProperty("退款状态")
    @JSONField(name = "status")
    private String status;
    @ApiModelProperty("资金账户")
    @JSONField(name = "funds_account")
    private String funds_account;
    @ApiModelProperty("金额信息")
    @JSONField(name = "amount")
    private Amount amount;
    @ApiModelProperty("优惠退款信息")
    @JSONField(name = "promotion_detail")
    private PromotionDetail promotionDetail;

    @Data
    @ApiModel("金额信息")
    static class Amount {
        @Data
        @ApiModel("退款出资账户及金额")
        static class From {
            @ApiModelProperty("出资账户类型")
            @JSONField(name = "account")
            private String account;
            @ApiModelProperty("出资金额")
            @JSONField(name = "amount")
            private Integer amount;
        }

        @ApiModelProperty("订单金额")
        @JSONField(name = "total")
        private Integer total;
        @ApiModelProperty("用户退款金额")
        @JSONField(name = "refund")
        private Integer refund;
        @ApiModelProperty("用户退款金额")
        @JSONField(name = "payer_refund")
        private Integer payerRefund;
        @ApiModelProperty("应结退款金额")
        @JSONField(name = "settlement_refund")
        private Integer settlementRefund;
        @ApiModelProperty("应结订单金额")
        @JSONField(name = "settlement_total")
        private Integer settlementTotal;
        @ApiModelProperty("优惠退款金额")
        @JSONField(name = "discount_refund")
        private Integer discountRefund;
        @ApiModelProperty("退款币种")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("手续费退款金额")
        @JSONField(name = "refund_fee")
        private Integer refundFee;
    }

    @Data
    @ApiModel("优惠退款信息")
    static class PromotionDetail {
        @Data
        @ApiModel("商品列表")
        static class GoodsDetail {
            @ApiModelProperty("微信支付退款单号")
            @JSONField(name = "merchant_goods_id")
            private String merchantGoodsId;
            @ApiModelProperty("微信支付退款单号")
            @JSONField(name = "wechatpay_goods_id")
            private String wechatpayGoodsId;
            @ApiModelProperty("微信支付退款单号")
            @JSONField(name = "goods_name")
            private String goodsName;
            @ApiModelProperty("微信支付退款单号")
            @JSONField(name = "unit_price")
            private Integer unitPrice;
            @ApiModelProperty("微信支付退款单号")
            @JSONField(name = "refund_amount")
            private Integer refundAmount;
            @ApiModelProperty("微信支付退款单号")
            @JSONField(name = "refund_quantity")
            private Integer refundQuantity;
        }

        @ApiModelProperty("券ID")
        @JSONField(name = "promotion_id")
        private String promotion_id;
        @ApiModelProperty("优惠范围")
        @JSONField(name = "scope")
        private String scope;
        @ApiModelProperty("优惠类型")
        @JSONField(name = "type")
        private String type;
        @ApiModelProperty("优惠券面额")
        @JSONField(name = "amount")
        private Integer amount;
        @ApiModelProperty("优惠退款金额")
        @JSONField(name = "refund_amount")
        private Integer refundAmount;
        @ApiModelProperty("商品列表")
        @JSONField(name = "goods_detail")
        private GoodsDetail goodsDetail;
    }
}
