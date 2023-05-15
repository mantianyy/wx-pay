package com.wx.dto.apply;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jiang漫天
 */
@ApiModel("下单接口参数模型")
@Data
public class ApplyRefundDto {
    @ApiModelProperty("返回信息")
    @JSONField(name = "transaction_id")
    private String transactionId;
    @ApiModelProperty("返回信息")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("返回信息")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    @ApiModelProperty("返回信息")
    @JSONField(name = "reason")
    private String reason;
    @ApiModelProperty("返回信息")
    @JSONField(name = "notify_url")
    private String notifyUrl;
    @ApiModelProperty("返回信息")
    @JSONField(name = "funds_account")
    private String fundsAccount;
    @ApiModelProperty("返回信息")
    @JSONField(name = "amount")
    private Amount amount;

    @ApiModelProperty("退款商品")
    @JSONField(name = "goods_detail")
    private List<RefundGoods> goodsDetail;

    @Data
    @ApiModel("amount")
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
        @ApiModelProperty("退款金额")
        @JSONField(name = "refund")
        private Integer refund;
        @ApiModelProperty("原订单金额")
        @JSONField(name = "total")
        private Integer total;
        @ApiModelProperty("退款币种")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("退款出资账户及金额")
        @JSONField(name = "from")
        private From from;
    }

    @ApiModel(value = "退款商品")
    @Data
    public class RefundGoods {
        @ApiModelProperty(value = "商户侧商品编码")
        @JSONField(name = "merchant_goods_id")
        private String merchantGoodsId;

        @ApiModelProperty(value = "微信支付商品编码")
        @JSONField(name = "wechatpay_goods_id")
        private String wechatpayGoodsId;

        @ApiModelProperty(value = "商品名称")
        @JSONField(name = "goods_name")
        private String goodsName;

        @ApiModelProperty(value = "商品单价")
        @JSONField(name = "unit_price")
        private Integer unitPrice;

        @ApiModelProperty(value = "商品退款金额")
        @JSONField(name = "refund_amount")
        private Integer refundAmount;

        @ApiModelProperty(value = "商品退货数量")
        @JSONField(name = "refund_quantity")
        private Integer refundQuantity;
    }
}
