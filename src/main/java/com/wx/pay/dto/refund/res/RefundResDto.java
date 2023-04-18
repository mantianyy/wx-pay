package com.wx.pay.dto.refund.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.pay.common.ChannelEnum;
import com.wx.pay.common.FundAccountEnum;
import com.wx.pay.common.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("退款返回模型")
@Data
public class RefundResDto {
    @ApiModelProperty("微信支付退款单号")
    @JSONField(name="refund_id")
    private String refundId;

    @ApiModelProperty("商户退款单号")
    @JSONField(name="out_refund_no")
    private String outRefundNo;

    @ApiModelProperty("微信支付订单号")
    @JSONField(name="transaction_id")
    private String transactionId;

    @ApiModelProperty("商户订单号")
    @JSONField(name="out_trade_no")
    private String outTradeNo;

    @ApiModelProperty("退款渠道")
    @JSONField(name="channel")
    private ChannelEnum channel;

    @ApiModelProperty("退款入账账户")
    @JSONField(name="user_received_account")
    private String userReceivedAccount;

    @ApiModelProperty("退款成功时间")
    @JSONField(name="success_time")
    private String successTime;

    @ApiModelProperty("退款创建时间")
    @JSONField(name="create_time")
    private String createTime;

    @ApiModelProperty("退款状态")
    @JSONField(name="status")
    private StatusEnum statusEnum;

    @ApiModelProperty("资金账户")
    @JSONField(name="funds_account")
    private FundAccountEnum fundAccountEnum;

    @ApiModelProperty("金额信息")
    @JSONField(name="amount")
    private Amount2Dto amount2Dto;
}
