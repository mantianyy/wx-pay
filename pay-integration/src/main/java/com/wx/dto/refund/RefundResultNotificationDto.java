package com.wx.dto.refund;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("退款结果通知入参模型")
public class RefundResultNotificationDto {
    @ApiModelProperty("通知ID")
    @JSONField(name = "id")
    private String id;
    @ApiModelProperty("通知创建时间")
    @JSONField(name = "create_time")
    private String createTime;
    @ApiModelProperty("通知类型")
    @JSONField(name = "event_type")
    private String eventType;
    @ApiModelProperty("通知简要说明")
    @JSONField(name = "summary")
    private String summary;
    @ApiModelProperty("通知数据类型")
    @JSONField(name = "resource_type")
    private String resourceType;
    @ApiModelProperty("通知数据")
    @JSONField(name = "resource")
    private Resource resource;

    @Data
    @ApiModel("退款结果通知入参模型")
    static class Resource{
        @ApiModelProperty("加密算法类型")
        @JSONField(name = "algorithm")
        private String algorithm;
        @ApiModelProperty("加密前的对象类型")
        @JSONField(name = "original_type")
        private String original_type;
        @ApiModelProperty("数据密文")
        @JSONField(name = "ciphertext")
        private String ciphertext;
        @ApiModelProperty("附加数据")
        @JSONField(name = "associated_data")
        private String associated_data;
        @ApiModelProperty("随机串")
        @JSONField(name = "nonce")
        private String nonce;
    }


    @ApiModelProperty("商户退款单号")
    @JSONField(name = "mchid")
    private String mchId;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "transaction_id")
    private String transactionId;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "refund_id")
    private String refundId;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "refund_status")
    private String refundStatus;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "success_time")
    private String successTime;
    @ApiModelProperty("商户退款单号")
    @JSONField(name = "user_received_account")
    private String userReceivedAccount;
    @ApiModelProperty("金额信息")
    @JSONField(name = "amount")
    private Amount amount;

    @Data
    @ApiModel("金额信息")
    static class Amount {
        @ApiModelProperty("商户退款单号")
        @JSONField(name = "total")
        private Integer total;
        @ApiModelProperty("商户退款单号")
        @JSONField(name = "refund")
        private Integer refund;
        @ApiModelProperty("商户退款单号")
        @JSONField(name = "payer_total")
        private Integer payerTotal;
        @ApiModelProperty("商户退款单号")
        @JSONField(name = "payer_refund")
        private Integer payerRefund;
    }

}
