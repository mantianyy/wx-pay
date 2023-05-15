package com.wx.dto.refund;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("退款结果通知入参模型")
public class RefundResultNotificationDto {

    @ApiModelProperty("直连商户号")
    @JSONField(name = "mchid")
    private String mchId;


    @ApiModelProperty("微信支付订单号")
    @JSONField(name = "transaction_id")
    private String transactionId;

    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradenNo;

    @ApiModelProperty(value = "商户退款号")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;


    @ApiModelProperty("微信支付退款单号")
    @JSONField(name = "refund_id")
    private String refundId;

    @ApiModelProperty("退款状态")
    @JSONField(name = "status")
    private String status;

    @ApiModelProperty("退款成功时间")
    @JSONField(name = "success_time")
    private String successTime;

    @ApiModelProperty("退款入账账户")
    @JSONField(name = "user_received_account")
    private String userReceivedAccount;

    @ApiModelProperty("金额信息")
    @JSONField(name = "amount")
    private Amount amount;


    @Data
    @ApiModel(value = "金额信息")
    public static class Amount{
        @ApiModelProperty(name = "订单金额")
        @JSONField(name = "total")
        private Integer total;

        @ApiModelProperty(name = "退款金额")
        @JSONField(name = "refund")
        private Integer refund;

        @ApiModelProperty(name = "用户支付金额")
        @JSONField(name = "payer_total")
        private Integer payerTotal;

        @ApiModelProperty(name = "用户退款金额")
        @JSONField(name = "payer_refund")
        private Integer payerRefund;

    }
}
