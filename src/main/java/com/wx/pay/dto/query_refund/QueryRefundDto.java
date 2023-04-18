package com.wx.pay.dto.query_refund;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.pay.dto.refund.res.Amount2Dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("退款信息查询模型")
public class QueryRefundDto {
    @ApiModelProperty("微信支付退款单号")
    @JSONField(name = "refund_id")
    private String refundId;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    @ApiModelProperty("微信支付订单号")
    @JSONField(name = "transaction_id")
    private String transactionId;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("退款渠道")
    @JSONField(name = "channel")
    private String channel;
    @ApiModelProperty("退款入账账户")
    @JSONField(name = "user_received_account")
    private String userReceivedAccount;
    @ApiModelProperty("退款成功时间")
    @JSONField(name = "success_time")
    private String success_time;
    @ApiModelProperty("退款创建时间")
    @JSONField(name = "success_time")
    private String successTime;
    @ApiModelProperty("退款状态")
    @JSONField(name = "create_time")
    private String createTime;
    @ApiModelProperty("")
    @JSONField(name = "status")
    private String status;
    @ApiModelProperty("资金账户")
    @JSONField(name = "funds_account")
    private String fundsAccount;
    @ApiModelProperty("金额信息")
    @JSONField(name = "amount")
    private Amount2Dto amount;
    @ApiModelProperty("优惠退款信息")
    @JSONField(name = "promotion_detail")
    private List<PromotionDetail> promotionDetailList;

}
