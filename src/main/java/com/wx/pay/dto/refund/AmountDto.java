package com.wx.pay.dto.refund;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wx.pay.common.CurrencyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("金额信息")
@Data
public class AmountDto {

    @ApiModelProperty("退款金额")
    @JSONField(name = "refund")
    private Integer refund;

    @ApiModelProperty("退款出资账户及金额")
    @JSONField(name = "from")
    private List<RefundSourceDto> from;

    @ApiModelProperty("原订单金额")
    @JSONField(name = "total")
    private Integer total;

    @ApiModelProperty("退款币种")
    @JSONField(name = "currency")
    @JsonEnumDefaultValue
    private CurrencyEnum currencyEnum;
}
