package com.wx.pay.dto.refund;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wx.pay.common.AccountEnum;
import com.wx.pay.common.FundAccountEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jiang漫天
 */
@ApiModel(value = "申请退款信息")
@Data
public class RefundDto {

    @ApiModelProperty(value = "微信支付订单号")
    @JSONField(name = "transaction_id")
    private String transactionId;

    @ApiModelProperty(value = "商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @ApiModelProperty(value = "商户退款号")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;

    @ApiModelProperty(value = "退款原因")
    @JSONField(name = "reason")
    private String reason;

    @ApiModelProperty(value = "退款结果回调url")
    @JSONField(name = "notify_url")
    private String notifyUrl;

    @ApiModelProperty(value = "退款资金来源")
    @JSONField(name = "funds_account")
    @JsonEnumDefaultValue
    private FundAccountEnum fundAccountEnum;

    @ApiModelProperty(value = "金额信息")
    @JSONField(name = "amount")
    private AmountDto amountDto;

    @ApiModelProperty(value = "退款商品")
    @JSONField(name = "goods_detail")
    private List<RefundGoods> goodsList;
}
