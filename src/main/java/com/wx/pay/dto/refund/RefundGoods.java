package com.wx.pay.dto.refund;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
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
