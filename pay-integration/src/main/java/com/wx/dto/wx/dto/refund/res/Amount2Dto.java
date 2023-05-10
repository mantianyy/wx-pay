package com.wx.dto.wx.dto.refund.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.common.CurrencyEnum;
import com.wx.dto.wx.dto.refund.RefundSourceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("金额信息返回模型")
@Data
public class Amount2Dto {

    @ApiModelProperty("订单金额")
    @JSONField(name = "total")
    private Integer total;

    @ApiModelProperty("退款金额")
    @JSONField(name = "refund")
    private Integer refund;

    @ApiModelProperty("退款出资账户及金额")
    @JSONField(name = "from")
    private List<RefundSourceDto> from;

    @ApiModelProperty("用户支付金额")
    @JSONField(name = "payer_total")
    private String payerTotal;

    @ApiModelProperty("用户退款金额")
    @JSONField(name = "payer_refund")
    private Integer payerRefund;

    @ApiModelProperty("应结退款金额")
    @JSONField(name = "settlement_refund")
    private Integer settlementRefund;

    @ApiModelProperty("应结算金额")
    @JSONField(name = "settlement_total")
    private Integer settlementTotal;

    @ApiModelProperty("优惠退款金额")
    @JSONField(name = "discount_refund")
    private Integer discountRefund;

    @ApiModelProperty("退款币种")
    @JSONField(name = "currency")
    private CurrencyEnum currency;

    @ApiModelProperty("手续费退款金额")
    @JSONField(name = "refund_fee")
    private Integer refundFee;
}
