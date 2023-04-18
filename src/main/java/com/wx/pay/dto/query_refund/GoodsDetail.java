package com.wx.pay.dto.query_refund;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品列表信息")
public class GoodsDetail {
    @ApiModelProperty("商户侧商品编码")
    @JSONField(name = "merchant_goods_id")
    private String merchantGoodsId;
    @ApiModelProperty("微信支付商品编码")
    @JSONField(name = "wechatpay_goods_id")
    private String wechatpayGoodsId;
    @ApiModelProperty("商品名称")
    @JSONField(name = "goods_name")
    private String goodsName;
    @ApiModelProperty("商品单价")
    @JSONField(name = "unit_price")
    private Integer unitPrice;
    @ApiModelProperty("商品退款金额")
    @JSONField(name = "refund_amount")
    private Integer refundAmount;
    @ApiModelProperty("商品退货数量")
    @JSONField(name = "refund_quantity")
    private Integer refundQuantity;
}