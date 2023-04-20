package com.wx.pay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AmountDto {

    @ApiModelProperty("货币类型")
    @JSONField(name = "currency")
    @JsonEnumDefaultValue
    private Enum currency;
    @ApiModelProperty("总金额")
    @JSONField(name = "total")
    private Integer total;
}
