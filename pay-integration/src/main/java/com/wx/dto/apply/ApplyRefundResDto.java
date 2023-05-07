package com.wx.dto.apply;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jiang漫天
 */
@ApiModel("下单接口返回信息")
public class ApplyRefundResDto {

    @ApiModelProperty("微信支付退款单号")
    @JSONField(name = "refund_id")
    private String refund_id;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "out_refund_no")
    private String out_refund_no;
    @ApiModelProperty("微信支付订单号")
    @JSONField(name = "transaction_id")
    private String transaction_id;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String out_trade_no;
    @ApiModelProperty("退款入账账户")
    @JSONField(name = "user_received_account")
    private String user_received_account;
    @ApiModelProperty("退款成功时间")
    @JSONField(name = "success_time")
    private String success_time;
    @ApiModelProperty("支付者")
    @JSONField(name = "create_time")
    private String create_time;
    @ApiModelProperty("退款状态")
    @JSONField(name = "status")
    private String status;
    @ApiModelProperty("资金账户")
    @JSONField(name = "funds_account")
    private String funds_account;


    @ApiModelProperty("金额信息")
    @JSONField(name = "amount")
    private Amount amount;

    @ApiModelProperty("支付者")
    @JSONField(name = "promotion_detail")
    private List<PromotionDetail> promotionDetail;


    @ApiModel("金额信息")
    @Data
    static class Amount {
        @ApiModel("退款出资账户及金额")
        @Data
        static class From {
            @ApiModelProperty("订单金额")
            @JSONField(name = "account")
            private String account;
            @ApiModelProperty("退款金额")
            @JSONField(name = "amount")
            private Integer amount;
        }

        @ApiModelProperty("用户支付金额")
        @JSONField(name = "payer_total")
        private Integer payer_total;
        @ApiModelProperty("用户退款金额")
        @JSONField(name = "payer_refund")
        private Integer payer_refund;
        @ApiModelProperty("应结订单金额")
        @JSONField(name = "settlement_refund")
        private Integer settlement_refund;
        @ApiModelProperty("应结订单金额")
        @JSONField(name = "settlement_total")
        private Integer settlement_total;
        @ApiModelProperty("优惠退款金额")
        @JSONField(name = "discount_refund")
        private Integer discount_refund;
        @ApiModelProperty("退款币种")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("手续费退款金额")
        @JSONField(name = "refund_fee")
        private Integer refund_fee;
    }

    static class PromotionDetail {
        @Data
        @ApiModel("商品列表")
        static class GoodsDetail {
            @ApiModelProperty("支付者")
            @JSONField(name = "merchant_goods_id")
            private String merchant_goods_id;
            @ApiModelProperty("支付者")
            @JSONField(name = "wechatpay_goods_id")
            private String wechatpay_goods_id;
            @ApiModelProperty("支付者")
            @JSONField(name = "goods_name")
            private String goods_name;
            @ApiModelProperty("支付者")
            @JSONField(name = "unit_price")
            private Integer unit_price;
            @ApiModelProperty("支付者")
            @JSONField(name = "refund_amount")
            private Integer refund_amount;
            @ApiModelProperty("支付者")
            @JSONField(name = "refund_quantity")
            private Integer refund_quantity;
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
        @ApiModelProperty("优惠退款金额")
        @JSONField(name = "refund_amount")
        private String refund_amount;
        @ApiModelProperty("商品列表")
        @JSONField(name = "goods_detail")
        private List<GoodsDetail> goodsDetail;
    }
}
