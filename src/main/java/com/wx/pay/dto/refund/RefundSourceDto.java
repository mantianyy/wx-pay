package com.wx.pay.dto.refund;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wx.pay.common.AccountEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
@ApiModel("退款出资账户及金额")
@Data
public class RefundSourceDto {

    @ApiModelProperty("出资账户类型")
    @JSONField(name = "account")
    @JsonEnumDefaultValue
    private AccountEnum account;

    @ApiModelProperty("出资金额")
    @JSONField(name = "amount")
    private Integer amount;
}
