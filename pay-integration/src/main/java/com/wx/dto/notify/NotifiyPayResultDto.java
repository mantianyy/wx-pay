package com.wx.dto.notify;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("支付订单通知接口参数模型")
public class NotifiyPayResultDto {
    @ApiModelProperty("应用ID")
    @JSONField(name = "appid")
    private String appid;
    @ApiModelProperty("商户号")
    @JSONField(name = "mchid")
    private String mchid;
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
    @ApiModelProperty("交易状态描述")
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
    @ApiModelProperty("订单金额")
    @JSONField(name = "amount")
    private Amount amount;
    @ApiModelProperty("场景信息")
    @JSONField(name = "scene_info")
    private SceneInfo sceneInfo;
    @ApiModelProperty("优惠功能")
    @JSONField(name = "promotion_detail")
    private PromotionDetail promotionDetail;

    @ApiModel("订单金额")
    static class Payer {
        @ApiModelProperty("用户标识")
        @JSONField(name = "openid")
        private String openid;
    }

    @ApiModel("订单金额")
    static class Amount {
        @ApiModelProperty("总金额")
        @JSONField(name = "total")
        private int total;
        @ApiModelProperty("用户支付金额")
        @JSONField(name = "payer_total")
        private int payerTotal;
        @ApiModelProperty("货币类型")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("用户支付币种")
        @JSONField(name = "payer_currency")
        private String payerCurrency;
    }

    @ApiModel("场景信息")
    static class SceneInfo {
        @ApiModelProperty("商户端设备号")
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
        private Integer wechatpayContribute;
        @ApiModelProperty("商户出资")
        @JSONField(name = "merchant_contribute")
        private Integer merchantContribute;
        @ApiModelProperty("其他出资")
        @JSONField(name = "other_contribute")
        private Integer otherContribute;
        @ApiModelProperty("优惠币种")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("单品列表")
        @JSONField(name = "goods_detail")
        private List<GoodsDetail> goodsDetails;

        @ApiModel("单品列表")
        static class GoodsDetail {
            @ApiModelProperty("商品编码")
            @JSONField(name = "goods_id")
            private String goodsId;
            @ApiModelProperty("商品数量")
            @JSONField(name = "quantity")
            private Integer quantity;
            @ApiModelProperty("商品单价")
            @JSONField(name = "unit_price")
            private Integer unit_price;
            @ApiModelProperty("商品优惠金额")
            @JSONField(name = "discount_amount")
            private Integer discountAmount;
            @ApiModelProperty("商品备注")
            @JSONField(name = "goods_remark")
            private String goodsRemark;
        }

    }


}
