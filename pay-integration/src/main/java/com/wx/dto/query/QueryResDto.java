package com.wx.dto.query;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询订单接口参数返回模型")
public class QueryResDto {
    @ApiModelProperty("应用ID")
    @JSONField(name = "appid")
    private String appId;
    @ApiModelProperty("直连商户号")
    @JSONField(name = "mchid")
    private String mchId;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("微信支付订单号")
    @JSONField(name = "transaction_id")
    private String transactionId;
    @ApiModelProperty("交易类型")
    @JSONField(name = "trade_type")
    private String tradeType;
    @ApiModelProperty("交易状态")
    @JSONField(name = "trade_state")
    private String tradeState;
    @ApiModelProperty("付款银行")
    @JSONField(name = "trade_state_desc")
    private String tradeStateDesc;
    @ApiModelProperty("付款银行")
    @JSONField(name = "bank_type")
    private String bankType;
    @ApiModelProperty("附加数据")
    @JSONField(name = "attach")
    private String attach;
    @ApiModelProperty("支付完成时间")
    @JSONField(name = "success_time")
    private String successTime;
    @ApiModelProperty("支付者")
    @JSONField(name = "payer")
    private Payer payer;
    @ApiModelProperty("金额")
    @JSONField(name = "amount")
    private Amount amount;
    @ApiModelProperty("场景信息")
    @JSONField(name = "scene_info")
    private SceneInfo sceneInfo;
    @ApiModelProperty("优惠功能")
    @JSONField(name = "promotion_detail")
    private PromotionDetail promotionDetail;

    @ApiModel("支付者")
    static class Payer {
        @ApiModelProperty("用户标识")
        @JSONField(name = "openid")
        private String openId;
    }

    @ApiModel("金额")
    static class Amount {
        @ApiModelProperty("用户终端IP")
        @JSONField(name = "total")
        private Integer total;
        @ApiModelProperty("用户终端IP")
        @JSONField(name = "payer_total")
        private Integer payerTotal;
        @ApiModelProperty("用户终端IP")
        @JSONField(name = "currency")
        private Integer currency;
        @ApiModelProperty("用户终端IP")
        @JSONField(name = "payer_currency")
        private Integer payerCurrency;
    }

    @ApiModel("场景信息")
    static class SceneInfo {
        @ApiModelProperty("用户终端IP")
        @JSONField(name = "device_id")
        private String deviceId;
    }

    @ApiModel("优惠功能")
    static class PromotionDetail {
        @ApiModelProperty("券ID")
        @JSONField(name = "coupon_id")
        private String couponId;
        @ApiModelProperty("优惠名称")
        @JSONField(name = "name")
        private String name;
        @ApiModelProperty("优惠范围")
        @JSONField(name = "scope")
        private String scope;
        @ApiModelProperty("优惠类型")
        @JSONField(name = "type")
        private String type;
        @ApiModelProperty("优惠券面额")
        @JSONField(name = "amount")
        private Integer amount;
        @ApiModelProperty("活动ID")
        @JSONField(name = "stock_id")
        private String stockId;
        @ApiModelProperty("微信出资")
        @JSONField(name = "wechatpay_contribute")
        private String wechatpayContribute;
        @ApiModelProperty("商户出资")
        @JSONField(name = "merchant_contribute")
        private String merchantContribute;
        @ApiModelProperty("其他出资")
        @JSONField(name = "other_contribute")
        private String otherContribute;
        @ApiModelProperty("单品列表")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("单品列表")
        @JSONField(name = "goods_detail")
        private GoodDetail goodDetail;

        @ApiModel("单品列表")
        static class GoodDetail {
            @ApiModelProperty("商品编码")
            @JSONField(name = "goods_id")
            private String goodsId;
            @ApiModelProperty("商品数量")
            @JSONField(name = "quantity")
            private Integer quantity;
            @ApiModelProperty("商品单价")
            @JSONField(name = "unit_price")
            private Integer unitPrice;
            @ApiModelProperty("商品优惠金额")
            @JSONField(name = "discount_amount")
            private Integer discountAmount;
            @ApiModelProperty("商品备注")
            @JSONField(name = "goods_remark")
            private String goodsRemark;
        }
    }

}
