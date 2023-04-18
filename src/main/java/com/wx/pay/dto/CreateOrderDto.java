package com.wx.pay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "createOrderDto", description = "微信统一下单")
public class CreateOrderDto {

    @ApiModelProperty("appid")
    @JSONField(name = "appid")
    private String appId;

    @ApiModelProperty("商户号")
    @JSONField(name = "mchid")
    private String mchId;

    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("商品描述")
    @JSONField(name = "description")
    private String desc;

    @ApiModelProperty("通知地址")
    @JSONField(name = "notify_url")
    private String notifyUrl;
    @ApiModelProperty("订单金额")
    @JSONField(name = "amount")
    private AmountDto amountDto;

    @ApiModelProperty("支付者")
    @JSONField(name = "payer")
    private PayerDto payerDto;
}
