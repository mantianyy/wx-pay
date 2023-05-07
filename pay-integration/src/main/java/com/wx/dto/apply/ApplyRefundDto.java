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
    private String transaction_id;
    @ApiModelProperty("返回信息")
    @JSONField(name = "out_trade_no")
    private String out_trade_no;
    @ApiModelProperty("返回信息")
    @JSONField(name = "out_refund_no")
    private String out_refund_no;
    @ApiModelProperty("返回信息")
    @JSONField(name = "reason")
    private String reason;
    @ApiModelProperty("返回信息")
    @JSONField(name = "notify_url")
    private String notify_url;
    @ApiModelProperty("返回信息")
    @JSONField(name = "funds_account")
    private String funds_account;
    @ApiModelProperty("返回信息")
    @JSONField(name = "amount")
    private Amount amount;

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
        private Integer currency;
    }
}
